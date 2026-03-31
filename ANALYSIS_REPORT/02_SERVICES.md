# 2. SERVICES ANALYSIS

## Overview
- **Count:** 4 services
- **Total Lines:** ~280
- **Pattern:** Inconsistent, some with infrastructure leaks

## Detailed Analysis

### A. RestauranteService (110 lines)

**Methods (7 total):**
1. listar() - @Transactional(readOnly=true) - Returns all
2. listarComFreteGratis() - Uses Specifications
3. findFirst() - Custom repository method
4. buscarPorId() - Throws exception
5. salvar() - Calls CozinhaService (tight coupling)
6. atualizar() - Complex merge with exclusions
7. atualizarParcial() - Reflection-based update

**Issue 1: Service-to-Service Dependency**
\\\
RestauranteService → CozinhaService → CozinhaRepository
\\\
Creates tight coupling, difficult to test

**Issue 2: Reflection-based Merge (lines 81-104)**
\\\java
private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
    ObjectMapper objectMapper = new ObjectMapper();  // ✗ Created per call
    objectMapper.configure(...);
    Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
    camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
        Field field = findField(Restaurante.class, nomePropriedade);  // ✗ String field name
        field.setAccessible(true);
        Object novoValor = getField(field, restauranteOrigem);
        setField(field, restauranteDestino, novoValor);  // ✗ Reflection
    });
}
\\\
Problems:
- ObjectMapper created per method (inefficient)
- Reflection-based field access (fragile)
- Magic strings for field names
- Error-prone and difficult to debug

**Issue 3: Magic Strings for Property Exclusion**
\\\java
copyProperties(restaurante, restauranteAtual,
    "id", "cozinha", "formasPagamento", "endereco", "dataCadastro")
\\\
Not maintainable, scattered across methods

**Issue 4: Complex Update Logic (lines 58-79)**
Different behavior based on whether cozinha is null
Duplicated property copying code

### B. EstadoService (71 lines)

**Methods (6 total):**
1. listar()
2. buscarPorId()
3. salvar()
4. atualizar()
5. deletar() - Complex exception handling
6. getOrElseThrow() - Private helper

**Critical Issue: EntityManager Injection**
\\\java
@PersistenceContext
private EntityManager entityManager;  // ✗ Infrastructure leak
\\\

**Pattern: ifPresentOrElse() for exception handling**
\\\java
estadoRepository.findById(id).ifPresentOrElse(
    estado -> {
        try {
            estadoRepository.delete(estado);
            estadoRepository.flush();
        } catch (DataIntegrityViolationException | PersistenceException e) {
            throw new EntityInUseException(...);
        }
    },
    () -> throw new StateNotFoundException(id)
);
\\\

**Issues:**
- EntityManager in service (architectural leak)
- Spring exceptions mixed with domain exceptions
- Complex exception handling in service

### C. CidadeService (70 lines)

**Methods (6 total):**
1. listar()
2. buscarPorId()
3. salvar() - Validates Estado
4. atualizar()
5. deletar()
6. getOrElseThrow() - Private helper

**Critical Issue: Direct Repository Access**
\\\java
Estado estado = estadoRepository.findById(cidade.getEstado().getId())
    .orElseThrow(() -> new MyEntityNotFoundException(...));
\\\
Should use EstadoService.getCozinhaOrElseThrow() instead

**Issues:**
- Direct EstadoRepository injection (should use service)
- Repeated validation logic
- No separation of concerns

### D. CozinhaService (67 lines)

**Methods (5 total + 1 public helper):**
1. listar()
2. buscarPorId()
3. adicionar()
4. atualizar() - DOESN'T SAVE!
5. deletar()
6. getCozinhaOrElseThrow() - PUBLIC HELPER

**Critical Bug: atualizar() Missing Save**
\\\java
@Transactional
public Cozinha atualizar(Long id, Cozinha cozinha) {
    Cozinha cozinhaAtual = getCozinhaOrElseThrow(id);
    copyProperties(cozinha, cozinhaAtual, "id");
    return cozinhaAtual;  // ✗ NO SAVE CALL!
    // Relies on Hibernate dirty-checking
}
\\\

**Issues:**
- Implicit persistence (dangerous)
- Public helper method (exposed internals)
- Inconsistent with other services

## Cross-Service Patterns

### Transactional Pattern (Good)
- @Transactional(readOnly=true) for queries
- @Transactional for modifications

### Helper Methods Pattern (Mixed)
- RestauranteService: private helpers
- CozinhaService: public helper (exposed)
- EstadoService: private helper

### Exception Handling (Inconsistent)
- RestauranteService: Throws from helpers
- EstadoService: Complex try-catch
- CidadeService: Direct throw
- CozinhaService: Direct throw

## Summary Issues

| Issue | Impact | Frequency | Severity |
|-------|--------|-----------|----------|
| Service dependencies | Coupling | RestauranteService | High |
| Reflection merge | Fragility | RestauranteService | High |
| Magic strings | Maintenance | All services | Medium |
| EntityManager injection | Architectural leak | EstadoService | Medium |
| Direct repo access | Coupling | CidadeService | Medium |
| Implicit persistence | Bugs | CozinhaService | Medium |
| Public helpers | Encapsulation | CozinhaService | Low |

## Migration Path

1. **Create Application Services**
   - Extract use case logic
   - Remove service-to-service calls
   - Inject repository ports instead

2. **Add DTOs**
   - Decouple internal from external
   - Add validation at boundaries

3. **Extract Domain Services**
   - Move business logic from services
   - Domain services work with entities
   - Application services work with DTOs

4. **Replace Reflection**
   - Use constructors instead
   - Use builder pattern
   - Type-safe updates

5. **Centralize Configuration**
   - ObjectMapper as bean
   - Property exclusion strategies
   - Reusable mappers
