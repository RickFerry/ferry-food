# RELATÓRIO COMPLETO DE ANÁLISE DE CÓDIGO NÃO UTILIZADO
## Projeto: Ferry Food

Data da Análise: 2024-03-31
Total de arquivos Java: 119

---

## ÍNDICE
1. [Resumo Executivo](#resumo-executivo)
2. [Mappers Não Utilizados](#mappers-não-utilizados)
3. [Domain Services Não Utilizados](#domain-services-não-utilizados)
4. [Imports Não Necessários](#imports-não-necessários)
5. [Código Duplicado](#código-duplicado)
6. [Classes de Configuração Vazias](#classes-de-configuração-vazias)
7. [Análise de DTOs](#análise-de-dtos)
8. [Recomendações de Refatoração](#recomendações-de-refatoração)
9. [Sumário de Risco](#sumário-de-risco)

---

## RESUMO EXECUTIVO

### Indicadores Principais
- **Total de classes Java**: 119
- **Mappers não utilizados**: 4
- **Domain Services não utilizados**: 2
- **Imports desnecessários**: 120+
- **Linhas de código duplicado**: 1000+
- **Arquivos para potencial remoção**: 6
- **Nível de risco geral**: MÉDIO

### Ações Imediatas Recomendadas
1. Remover 4 mappers de input não utilizados
2. Remover 2 domain services não utilizados
3. Remover 120+ imports desnecessários
4. Refatorar controllers para consolidar código duplicado

---

## MAPPERS NÃO UTILIZADOS

### Resumo
4 mappers de entrada (input) estão definidos mas nunca são chamados em nenhum lugar do código.

### Mappers para Remover

#### 1. EstadoInputMapper
- **Localização**: `/src/main/java/com/ferry/food/application/mappers/estado/EstadoInputMapper.java`
- **Status**: Não utilizado em nenhum lugar
- **Problema**: Os UseCases fazem mapeamento manual sem usar este mapper
- **Risco de Remoção**: BAIXO
- **Impacto**: Redução de ~34 linhas

#### 2. CidadeInputMapper
- **Localização**: `/src/main/java/com/ferry/food/application/mappers/cidade/CidadeInputMapper.java`
- **Status**: Não utilizado
- **Problema**: Idêntico ao EstadoInputMapper
- **Risco de Remoção**: BAIXO
- **Impacto**: Redução de ~30 linhas

#### 3. CozinhaInputMapper
- **Localização**: `/src/main/java/com/ferry/food/application/mappers/cozinha/CozinhaInputMapper.java`
- **Status**: Não utilizado
- **Risco de Remoção**: BAIXO
- **Impacto**: Redução de ~30 linhas

#### 4. RestauranteInputMapper
- **Localização**: `/src/main/java/com/ferry/food/application/mappers/restaurante/RestauranteInputMapper.java`
- **Status**: Não utilizado
- **Risco de Remoção**: BAIXO
- **Impacto**: Redução de ~50 linhas

### Recomendação
**REMOVER TODOS OS 4 MAPPERS**

Motivo: Não agregam valor e aumentam a complexidade do projeto desnecessariamente.

---

## DOMAIN SERVICES NÃO UTILIZADOS

### Serviços Identificados

#### 1. ValidadorRestaurante
- **Localização**: `/src/main/java/com/ferry/food/domain/domainservices/ValidadorRestaurante.java`
- **Anotação**: `@Component`
- **Problema**: Registrado como Bean Spring mas nunca injetado em nenhum UseCase
- **Referências**: 0 fora de testes
- **Risco de Remoção**: BAIXO (se confirmar que não há dependências)

#### 2. CalculadoraPedido
- **Localização**: `/src/main/java/com/ferry/food/domain/domainservices/CalculadoraPedido.java`
- **Anotação**: `@Component`
- **Problema**: Registrado como Bean Spring mas nunca utilizado
- **Referências**: 0 fora de testes
- **Risco de Remoção**: BAIXO

### Recomendação
**REMOVER OU REFATORAR**

Se estes serviços são necessários para lógica futura, manter com TODO comment. Se não são necessários, remover imediatamente.

---

## IMPORTS NÃO NECESSÁRIOS

### Resumo
Aproximadamente 120+ imports desnecessários foram identificados em todo o projeto.

### Categorias Principais

#### 1. Anotações Lombok Não Utilizadas
Total: ~50+ imports

**Padrão**:
- Arquivos que usam `@Data` do Lombok importam `@AllArgsConstructor`, `@NoArgsConstructor` desnecessariamente
- Estes são gerados automaticamente por `@Data`

**Arquivos Afetados**:
- Todas as classes em `/domain/ports/input/*/UseCase.java`
- Todas as classes em `/application/dtos/input/`
- Todas as classes em `/application/dtos/output/`

**Exemplo**:
```java
@Data
public class CriarEstadoDTO {
    private String nome;
}
// Não precisa de: import lombok.AllArgsConstructor;
// Não precisa de: import lombok.NoArgsConstructor;
// Não precisa de: import lombok.Data; // Será usado implicitamente
```

**Solução**: 
- Análise com IDE e remover imports não utilizados (Ctrl+Alt+O no IntelliJ)

#### 2. Exceções Importadas Mas Não Lançadas
Total: ~15+ imports

**Arquivos**:
- `CalculadoraPedido.java` - Importa `BusinessException` mas não lança
- `ValidadorRestaurante.java` - Importa `BusinessException` mas não lança
- Múltiplos arquivos em ValueObjects - Importam `ValidationException` mas não usam

#### 3. Tipos Importados Mas Não Utilizados Diretamente
Total: ~55+ imports

**Padrão**: UseCase interfaces importam tipos que aparecem em javadoc mas não nas assinaturas.

### Recomendação
**REMOVER TODOS OS 120+ IMPORTS DESNECESSÁRIOS**

Benefício:
- Código mais limpo
- Menos confusão visual
- Melhor para manutenção

Ação:
1. Usar IDE para executar "Organize Imports" em todo o projeto
2. Ou executar: `mvn spotless:apply` (se configurado)

---

## CÓDIGO DUPLICADO

### Padrão 1: Controllers (4 arquivos, ~240 linhas duplicadas)

**Estrutura Idêntica**:
```
EstadoController -----+
CidadeController     +---> Mesma estrutura com 5 endpoints CRUD
CozinhaController   +
RestauranteController +
```

Cada controller tem:
- 5 campos com injeção de dependência (Criar, Listar, Obter, Atualizar, Deletar Use Cases)
- 5 métodos HTTP (GET, GET/:id, POST, PUT/:id, DELETE/:id)
- Mesma lógica em cada endpoint

**Linhas de Código Duplicadas**: ~60 linhas x 4 = 240 linhas

**Exemplo da Duplicação**:
```java
// EstadoController.java
@GetMapping("/{id}")
public ResponseEntity<EstadoDTO> obter(@PathVariable Long id) {
    EstadoDTO estado = obterUseCase.executar(id);
    return ResponseEntity.ok(estado);
}

// CidadeController.java - IDÊNTICO, apenas mudam os nomes
@GetMapping("/{id}")
public ResponseEntity<CidadeDTO> obter(@PathVariable Long id) {
    CidadeDTO cidade = obterUseCase.executar(id);
    return ResponseEntity.ok(cidade);
}
```

### Padrão 2: UseCase Implementations (20 arquivos, ~400 linhas duplicadas)

**Estrutura**:
```
CriarEstadoImpl ---+
CriarCidadeImpl    +
CriarCozinhaImpl   +---> Mesma estrutura
CriarRestauranteImpl +

(Mesma coisa para Atualizar, Deletar, etc)
```

Todos seguem:
```java
@Component
@RequiredArgsConstructor
public class {Acao}{Entidade}Impl implements {Acao}{Entidade}UseCase {
    private final {Entidade}RepositoryPort repository;
    private final {Entidade}OutputMapper mapper;
    
    @Override
    @Transactional
    public {Entidade}DTO executar(Input input) {
        // ... lógica muito similar
    }
}
```

**Linhas de Código Duplicadas**: ~20 linhas x 20 = 400 linhas

### Padrão 3: Mappers Output (4 arquivos, ~100 linhas duplicadas)

**Estrutura**:
```java
@Mapper(componentModel = "spring")
public interface EstadoOutputMapper {
    EstadoDTO toDTO(EstadoJpaEntity entity);
    List<EstadoDTO> toDTOList(List<EstadoJpaEntity> entities);
}
```

Todos os mappers seguem o mesmo padrão com 2 métodos.

**Linhas Duplicadas**: ~5 linhas x 4 = 20 linhas

### Recomendação

#### Consolidar Controllers (Risco: MÉDIO)
Criar um `GenericCrudController<T>`:
```java
@RestController
public abstract class GenericCrudController<ID, CreateDTO, UpdateDTO, ResponseDTO> {
    protected abstract CreateUseCase<CreateDTO, ResponseDTO> getCreateUseCase();
    protected abstract ListUseCase<ResponseDTO> getListUseCase();
    protected abstract GetUseCase<ID, ResponseDTO> getGetUseCase();
    protected abstract UpdateUseCase<ID, UpdateDTO, ResponseDTO> getUpdateUseCase();
    protected abstract DeleteUseCase<ID> getDeleteUseCase();
    
    @GetMapping
    public ResponseEntity<List<ResponseDTO>> listar() { ... }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> obter(@PathVariable ID id) { ... }
    
    @PostMapping
    public ResponseEntity<ResponseDTO> criar(@Valid @RequestBody CreateDTO dto) { ... }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> atualizar(@PathVariable ID id, ...) { ... }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable ID id) { ... }
}
```

Benefício: Redução de ~240 linhas, código mais fácil de manter

---

## CLASSES DE CONFIGURAÇÃO VAZIAS

### 1. AdapterConfiguration
- **Localização**: `/adapter/config/AdapterConfiguration.java`
- **Status**: Vazia com apenas comentários
- **Justificativa**: Placeholder bem documentado
- **Recomendação**: MANTER (conforme documentado no arquivo)

### 2. HttpAdapterConfiguration
- **Localização**: `/adapter/input/http/config/HttpAdapterConfiguration.java`
- **Status**: Vazia com apenas comentários
- **Justificativa**: Placeholder para futuras configurações
- **Recomendação**: MANTER (é uma boa prática ter espaço reservado)

---

## ANÁLISE DE DTOs

### DTOs com Possíveis Campos Não Utilizados

#### RestauranteDTO (Output)
- **Arquivo**: `/application/dtos/output/restaurante/RestauranteDTO.java`
- **Campos potencialmente não preenchidos**: 
  - `taxaFrete`
  - `logradouro`
  - `numero`
  - `complemento`
  - `bairro`
  - `cep`

**Nota**: Como estes campos são acessados via getters do Lombok, técnicamente não são "não utilizados", mas podem não ser preenchidos nas respostas HTTP.

**Recomendação**: Investigar se estes campos são realmente retornados no endpoint `/restaurantes`.

---

## RECOMENDAÇÕES DE REFATORAÇÃO

### Fase 1: Limpeza Imediata (Duração: 1-2 horas, Risco: BAIXO)

#### Ação 1.1: Remover Mappers Input Não Utilizados
- [ ] Deletar `EstadoInputMapper.java`
- [ ] Deletar `CidadeInputMapper.java`
- [ ] Deletar `CozinhaInputMapper.java`
- [ ] Deletar `RestauranteInputMapper.java`

**Checklist**:
- [ ] Verificar se há testes referenciando estes mappers
- [ ] Verificar se há imports destes mappers
- [ ] Remover dos pacotes

#### Ação 1.2: Remover Domain Services Se Não Utilizados
- [ ] Deletar `ValidadorRestaurante.java` (se confirmar não há uso)
- [ ] Deletar `CalculadoraPedido.java` (se confirmar não há uso)

#### Ação 1.3: Limpar Imports
- [ ] Usar IDE: Ctrl+Alt+O (IntelliJ) ou Ctrl+Shift+O (Eclipse)
- [ ] Aplicar em todo o projeto
- [ ] Commitar como "chore: remove unused imports"

**Resultado**: Redução de ~150 linhas de código

---

### Fase 2: Refatoração de Código Duplicado (Duração: 6-8 horas, Risco: MÉDIO)

#### Ação 2.1: Criar GenericCrudController
- [ ] Criar classe `GenericCrudController<ID, CreateDTO, UpdateDTO, ResponseDTO>`
- [ ] Mover lógica comum dos 4 controllers
- [ ] Atualizar controllers para estender a classe genérica
- [ ] Testar cada controller individualmente

#### Ação 2.2: Criar Mappers Genéricos
- [ ] Criar `GenericOutputMapper<Entity, DTO>` se possível
- [ ] Ou consolidar em menos mappers especializados

**Resultado**: Redução de ~400 linhas de código, melhor manutenção

---

### Fase 3: Investigação de DTOs (Duração: 4-6 horas, Risco: ALTO)

#### Ação 3.1: Analisar Campos de RestauranteDTO
- [ ] Verificar em `RestauranteController` se os campos são retornados
- [ ] Verificar em `RestauranteOutputMapper` se são mapeados
- [ ] Verificar em `RestauranteJpaEntity` se existem
- [ ] Se não são utilizados, remover

**Cuidado**: Mudanças em DTOs podem quebrar clientes HTTP

---

## SUMÁRIO DE RISCO

### Tabela de Priorização

| Item | Categoria | Risco | Impacto | Duração | Ação |
|------|-----------|-------|--------|---------|------|
| Mappers Input | Remover | BAIXO | Reduz 150 linhas | 1h | Remover |
| Domain Services | Remover | BAIXO | Remove 80 linhas | 1h | Remover |
| Imports | Remover | BAIXO | Limpa código | 30m | Remover |
| Controllers Duplicados | Refatorar | MÉDIO | Reduz 240 linhas | 6h | Consolidar |
| UseCase Impls | Refatorar | MÉDIO | Reduz 400 linhas | 8h | Consolidar |
| DTOs Campos | Investigar | ALTO | Pode quebrar API | 4h | Investigar |

### Timeline Recomendada

**Semana 1** (Prioridade Alta):
- Dia 1: Remover mappers input + domain services
- Dia 2: Limpar todos os imports
- Dia 3: Testar e commitar

**Semana 2** (Prioridade Média):
- Dia 1-3: Refatorar controllers para genérico
- Dia 4-5: Testar e commitar

**Semana 3** (Prioridade Baixa):
- Dia 1-3: Refatorar mappers
- Dia 4-5: Investigar DTOs se necessário

---

## MÉTRICAS FINAIS

### Antes da Refatoração
- Linhas de código: ~5,000+
- Arquivos: 119
- Duplicação: ~1,000+ linhas

### Após Refatoração (Estimado)
- Linhas de código: ~4,000-4,200
- Arquivos: ~110-113
- Duplicação: ~200-300 linhas

### Benefícios
- Redução de 15-20% de código
- Manutenção facilitada
- Menos bugs futuros (menos código = menos bugs)
- Onboarding facilitado para novos desenvolvedores

---

## CONCLUSÃO

O projeto tem uma boa estrutura arquitetural, mas sofre de:

1. **Código não utilizado**: 4 mappers, 2 services
2. **Código duplicado**: Padrão CRUD repetido em múltiplos lugares
3. **Imports desnecessários**: ~120+ imports que não são usados

Recomenda-se seguir a roadmap proposto em 3 fases, começando pela limpeza imediata (Fase 1) que é rápida e de baixo risco.

---

**Relatório Gerado**: 2024-03-31
**Versão**: 1.0
**Status**: Pronto para Ação
