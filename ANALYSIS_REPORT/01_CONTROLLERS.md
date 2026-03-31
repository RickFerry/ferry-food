# 1. CONTROLLERS ANALYSIS

## Overview
- **Count:** 4 REST controllers
- **Total Lines:** ~250
- **Pattern:** Inconsistent error handling

## Controllers Detailed

### A. RestauranteController (76 lines)
Location: api/controller/RestauranteController.java

**Endpoints:**
- GET    /restaurantes              (List)
- GET    /restaurantes/{id}         (Find by ID) - try-catch
- GET    /restaurantes/com-frete-gratis  (Query)
- GET    /restaurantes/find-first   (First)
- POST   /restaurantes              (Create) - try-catch
- PUT    /restaurantes/{id}         (Update) - try-catch
- PATCH  /restaurantes/{id}         (Partial) - try-catch

**Pattern Issues:**
- try-catch blocks at controller level
- ResponseEntity<?> with mixed types
- Map<String, Object> for partial updates (not type-safe)
- UriComponentsBuilder scattered
- Business exception handling in controller

**Positive:**
- @RequiredArgsConstructor
- Proper REST conventions
- Location headers for creation

### B. EstadoController (47 lines)
Location: api/controller/EstadoController.java

**Pattern:** Better than Restaurante
- Relies on global exception handler
- No try-catch (cleaner)
- Direct ResponseEntity responses

**Issue:** Assumes implicit global handler

### C. CidadeController (67 lines)
Location: api/controller/CidadeController.java

**Pattern:** Similar to RestauranteController
- try-catch blocks
- Consistent error mapping

### D. CozinhaController (62 lines)
Location: api/controller/CozinhaController.java

**Issues:**
- Null checking pattern
- Magic HTTP status codes (409, 404)
- Mixed error handling
- Different pattern from others

## Summary Issues

1. **Inconsistent Error Handling**
   - Some use try-catch, some don't
   - Some use magic numbers (409, 404)
   - Some check for null

2. **No Centralized Exception Handling**
   - @ExceptionHandler missing
   - ErrorResponse DTO missing
   - Error codes not defined

3. **Type Safety Issues**
   - Map<String, Object> for updates
   - ResponseEntity<?>
   - Magic strings

4. **Design Issues**
   - Service dependencies coupled
   - No DTOs (direct entity returns)
   - Validation missing

## Migration Path
- Create DTOs for input/output
- Implement global exception handler
- Add @RestControllerAdvice
- Extract to adapter pattern
- Add input validation
