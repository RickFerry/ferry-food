# PLANO DE AÇÃO - LIMPEZA DE CÓDIGO NÃO UTILIZADO
## Ferry Food Project

**Data**: 2024-03-31
**Prioridade**: ALTA
**Risco Geral**: MÉDIO

---

## RESUMO EXECUTIVO

Este documento fornece um plano de ação passo a passo para remover código não utilizado e refatorar código duplicado no projeto Ferry Food.

### O que será alcançado:
- Remoção de 6 arquivos desnecessários
- Remoção de 120+ imports não utilizados
- Consolidação de ~660 linhas de código duplicado
- Redução total de ~15-20% do código

### Benefícios:
- Código mais limpo e fácil de manter
- Menos confusão para novos desenvolvedores
- Melhor performance de IDE
- Menos bugs relacionados a código morto

---

## FASE 1: LIMPEZA IMEDIATA ✓ FAZER AGORA
**Duração**: 1-2 horas | **Risco**: BAIXO | **Impacto**: 150 linhas removidas

### Passo 1.1: Remover Mappers Input Não Utilizados

#### Checklist:
- [ ] Abrir arquivo: `EstadoInputMapper.java`
  - Localização: `/src/main/java/com/ferry/food/application/mappers/estado/EstadoInputMapper.java`
  - Verificar: Não há nenhuma referência a esta classe
  - Ação: DELETE
  
- [ ] Abrir arquivo: `CidadeInputMapper.java`
  - Localização: `/src/main/java/com/ferry/food/application/mappers/cidade/CidadeInputMapper.java`
  - Verificar: Não há nenhuma referência a esta classe
  - Ação: DELETE
  
- [ ] Abrir arquivo: `CozinhaInputMapper.java`
  - Localização: `/src/main/java/com/ferry/food/application/mappers/cozinha/CozinhaInputMapper.java`
  - Verificar: Não há nenhuma referência a esta classe
  - Ação: DELETE
  
- [ ] Abrir arquivo: `RestauranteInputMapper.java`
  - Localização: `/src/main/java/com/ferry/food/application/mappers/restaurante/RestauranteInputMapper.java`
  - Verificar: Não há nenhuma referência a esta classe
  - Ação: DELETE

#### Verificação Final:
```bash
# Execute no terminal para confirmar que não há referências
grep -r "EstadoInputMapper\|CidadeInputMapper\|CozinhaInputMapper\|RestauranteInputMapper" src/
# Resultado: Nenhuma linha deve ser retornada
```

#### Commit:
```bash
git add -A
git commit -m "chore: remove unused input mappers

- Remove EstadoInputMapper (not used in any use case)
- Remove CidadeInputMapper (not used in any use case)
- Remove CozinhaInputMapper (not used in any use case)
- Remove RestauranteInputMapper (not used in any use case)

These mappers were defined but never injected or called in the application.
The use cases perform mapping manually without these mappers."
```

**Status**: ⏳ PENDENTE

---

### Passo 1.2: Verificar Domain Services

#### Checklist:
- [ ] Verificar `ValidadorRestaurante.java`
  - Localização: `/src/main/java/com/ferry/food/domain/domainservices/ValidadorRestaurante.java`
  - Ação: Verificar se é realmente necessário
  - Se não: DELETE
  
- [ ] Verificar `CalculadoraPedido.java`
  - Localização: `/src/main/java/com/ferry/food/domain/domainservices/CalculadoraPedido.java`
  - Ação: Verificar se é realmente necessário
  - Se não: DELETE

#### Verificação:
```bash
grep -r "ValidadorRestaurante\|CalculadoraPedido" src/main --include="*.java" | grep -v "test"
# Se retornar apenas a definição, está seguro remover
```

#### Opções:
- **Opção A**: Se for necessário no futuro, adicionar comentário:
  ```java
  /**
   * TODO: This service will be used for restaurant validation in future features.
   * Keep until required by business logic.
   */
  ```

- **Opção B**: Se não for necessário, DELETE

**Status**: ⏳ PENDENTE

---

### Passo 1.3: Remover Imports Não Necessários

#### Checklist (Automático no IntelliJ IDEA):
- [ ] Abrir projeto em IntelliJ IDEA
- [ ] Menu: Code → Optimize Imports...
- [ ] Selecionar: All files in project
- [ ] Clicar: Run
- [ ] Revisar mudanças (devem parecer seguras)
- [ ] Commit as changes

#### Checklist (Se não usar IntelliJ):
Para cada arquivo Java:
```bash
# Remover imports não utilizados manualmente
# ou usar ferramenta: mvn spotless:apply (se configurado)
```

#### Commit:
```bash
git add -A
git commit -m "chore: remove unused imports

- Remove redundant Lombok annotations (@AllArgsConstructor, @NoArgsConstructor)
- Remove unused exception imports
- Remove unused type imports
- Clean up all unnecessary imports across the project"
```

**Status**: ⏳ PENDENTE

---

## FASE 2: REFATORAÇÃO DE CÓDIGO DUPLICADO ⏱️ PRÓXIMA SEMANA
**Duração**: 6-8 horas | **Risco**: MÉDIO | **Impacto**: 400 linhas removidas

### Passo 2.1: Criar GenericCrudController

#### Checklist:
- [ ] Criar novo arquivo: `GenericCrudController.java`
  - Localização: `/src/main/java/com/ferry/food/adapter/input/http/controller/GenericCrudController.java`
  - Copiar código de template (ver arquivo: DETAILED_FILE_ANALYSIS.md)
  
- [ ] Testar compilação:
  ```bash
  mvn clean compile
  ```
  
- [ ] Atualizar `EstadoController.java`:
  - Remover 60 linhas de código duplicado
  - Estender `GenericCrudController`
  - Implementar métodos abstratos
  - Testar: `mvn test -Dtest=*EstadoController*`
  
- [ ] Atualizar `CidadeController.java`:
  - Remover 60 linhas de código duplicado
  - Estender `GenericCrudController`
  - Implementar métodos abstratos
  - Testar: `mvn test -Dtest=*CidadeController*`
  
- [ ] Atualizar `CozinhaController.java`:
  - Remover 60 linhas de código duplicado
  - Estender `GenericCrudController`
  - Implementar métodos abstratos
  - Testar: `mvn test -Dtest=*CozinhaController*`
  
- [ ] Atualizar `RestauranteController.java`:
  - Remover 60 linhas de código duplicado
  - Estender `GenericCrudController`
  - Implementar métodos abstratos
  - Testar: `mvn test -Dtest=*RestauranteController*`

#### Verificação:
```bash
# Executar todos os testes
mvn clean test

# Verificar se a aplicação inicia
mvn spring-boot:run
```

#### Commit:
```bash
git add -A
git commit -m "refactor: consolidate CRUD controllers with GenericCrudController

- Create GenericCrudController base class with common CRUD operations
- Update EstadoController to extend GenericCrudController
- Update CidadeController to extend GenericCrudController
- Update CozinhaController to extend GenericCrudController
- Update RestauranteController to extend GenericCrudController
- Reduce duplicated code by ~240 lines
- Improve maintainability with single source of truth"
```

**Status**: ⏳ PENDENTE

---

### Passo 2.2: Consolidar Mappers Output

#### Checklist:
- [ ] Analisar os 4 mappers output:
  - `EstadoOutputMapper.java`
  - `CidadeOutputMapper.java`
  - `CozinhaOutputMapper.java`
  - `RestauranteOutputMapper.java`
  
- [ ] Se possível, criar:
  - `GenericOutputMapper<Entity, DTO>`
  
- [ ] Ou, consolidar em interface comum:
  - Todos implementar interface `EntityMapper<Entity, DTO>`
  
- [ ] Testar compilação:
  ```bash
  mvn clean compile
  ```

#### Verificação:
```bash
# Rodar testes de mapper
mvn test -Dtest=*Mapper*
```

**Status**: ⏳ PENDENTE

---

## FASE 3: INVESTIGAÇÃO DE DTOs ❌ PRÓXIMO MÊS
**Duração**: 4-6 horas | **Risco**: ALTO | **Impacto**: 50-100 linhas

### ⚠️ CUIDADO: Mudanças em DTOs podem quebrar clientes HTTP

### Passo 3.1: Analisar RestauranteDTO

#### Checklist:
- [ ] Abrir: `RestauranteDTO.java`
  - Localização: `/src/main/java/com/ferry/food/application/dtos/output/restaurante/RestauranteDTO.java`
  
- [ ] Para cada campo questionável:
  - `taxaFrete`
  - `logradouro`
  - `numero`
  - `complemento`
  - `bairro`
  - `cep`
  
  Fazer:
  1. Verificar em `RestauranteController` se o campo é retornado
  2. Verificar em `RestauranteOutputMapper` se o campo é mapeado
  3. Verificar em `RestauranteJpaEntity` se o campo existe
  4. Testar endpoint: `GET /restaurantes/1` e ver resposta JSON
  
- [ ] Se campo NÃO for utilizado:
  - Antes de remover, verificar com Product/Cliente
  - Documentar remoção em breaking changes
  - Remover do DTO
  - Remover do mapper
  - REQUER NOVO TESTE

#### Verificação:
```bash
# Testar endpoint com cURL
curl http://localhost:8080/restaurantes/1

# Verificar se campos estão na resposta JSON
```

**Status**: ⏳ PENDENTE (APÓS APROVAÇÃO DO CLIENTE)

---

## RESUMO FINAL

### Arquivos Afetados por Fase:

**FASE 1 (Remover)**:
- [x] EstadoInputMapper.java (34 linhas)
- [x] CidadeInputMapper.java (30 linhas)
- [x] CozinhaInputMapper.java (30 linhas)
- [x] RestauranteInputMapper.java (50 linhas)
- [x] 120+ imports desnecessários
- **Total**: 144 linhas + imports

**FASE 2 (Refatorar)**:
- [x] Criar GenericCrudController.java (novo)
- [x] EstadoController.java (60 linhas duplicadas)
- [x] CidadeController.java (60 linhas duplicadas)
- [x] CozinhaController.java (60 linhas duplicadas)
- [x] RestauranteController.java (60 linhas duplicadas)
- **Total**: 240 linhas consolidadas

**FASE 3 (Investigar)**:
- [ ] RestauranteDTO.java (investigação necessária)
- [ ] RestauranteOutputMapper.java (investigação necessária)
- **Total**: 50-100 linhas potenciais

---

## MÉTRICAS PRÉ/PÓS REFATORAÇÃO

### Antes:
- Total de linhas: ~5,000+
- Arquivos Java: 119
- Linhas duplicadas: ~1,000+
- Mappers não utilizados: 4
- Domain services não utilizados: 2
- Imports desnecessários: 120+

### Depois (Estimado):
- Total de linhas: ~4,000-4,200
- Arquivos Java: 113-115
- Linhas duplicadas: ~200-300
- Mappers não utilizados: 0
- Domain services não utilizados: 0
- Imports desnecessários: 0

### Benefício:
- Redução de 15-20% do código
- 100% de manutenibilidade melhorada
- Tempo de onboarding reduzido para novos devs
- Menos possibilidade de bugs

---

## PRÓXIMOS PASSOS RECOMENDADOS

1. **Semana 1**: Executar FASE 1 (1-2 horas)
   - Remover mappers
   - Remover domain services se necessário
   - Limpar imports

2. **Semana 2**: Executar FASE 2 (6-8 horas)
   - Refatorar controllers
   - Consolidar mappers
   - Testar completamente

3. **Semana 3-4**: Executar FASE 3 (após aprovação)
   - Investigar DTOs
   - Remover campos não utilizados
   - Atualizar documentação

---

## FERRAMENTAS RECOMENDADAS

Para prevenção futura de código não utilizado:

1. **SonarQube**
   - Detectar código não utilizado automaticamente
   - Configurar no pipeline CI/CD

2. **IntelliJ IDEA Inspections**
   - Configurar inspeções para código não utilizado
   - Executar regularmente

3. **Spotbugs**
   - Análise de bytecode para código morto
   - Integração com Maven

---

## CONTATO E DÚVIDAS

Qualquer dúvida ou problema durante a execução:
1. Revisar este documento novamente
2. Consultar DETAILED_FILE_ANALYSIS.md para detalhes técnicos
3. Revisar UNUSED_CODE_ANALYSIS.md para análise completa

---

**Status Geral**: ⏳ AGUARDANDO EXECUÇÃO
**Próxima Ação**: Executar FASE 1
**Data Alvo**: SEMANA PRÓXIMA

