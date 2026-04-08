```markdown
# 🤖 Guia de Desenvolvimento Orientado a IA: OpenSpec + OpenCode

Este documento descreve o fluxo de trabalho oficial para o uso de agentes de Inteligência Artificial no desenvolvimento deste projeto. Ele combina a **governança e planejamento do OpenSpec** com o **motor de execução e edição do OpenCode**, tudo operado estritamente via terminal (PowerShell ou WSL2/Linux).

O objetivo é garantir arquitetura consistente (Arquitetura Hexagonal, Spring Boot, Java 21), qualidade de código e **privacidade absoluta dos dados corporativos**.

---

## 🔒 1. Configuração de Privacidade e Segurança (Obrigatório)

Antes de iniciar qualquer interação com a IA, é estritamente necessário bloquear o envio de telemetria e o compartilhamento de código em servidores externos.

### 1.1 Bloqueando a Telemetria do OpenSpec
Desative o "ping" de métricas de uso configurando a variável de ambiente no seu terminal.

**No PowerShell (Windows):**
Abra o PowerShell e adicione a variável ao seu perfil para que seja carregada automaticamente todos os dias:
```powershell
if (!(Test-Path -Path $PROFILE)) { New-Item -ItemType File -Path $PROFILE -Force }
Add-Content -Path $PROFILE -Value '$env:OPENSPEC_TELEMETRY="0"'
```
*(Feche e abra o terminal novamente para aplicar).*

**No WSL2 / Linux:**
Adicione a regra ao seu arquivo de perfil (ex: `~/.bashrc` ou `~/.zshrc`):
```bash
export OPENSPEC_TELEMETRY=0
```

### 1.2 Bloqueando o Vazamento de Sessões do OpenCode
Para garantir que nenhum desenvolvedor compartilhe o código proprietário acidentalmente, o arquivo `opencode.json` deve existir na raiz do projeto e **deve ser commitado no repositório**.

Crie o arquivo na raiz:
```powershell
New-Item -Path "opencode.json" -ItemType File
```

Adicione o seguinte conteúdo:
```json
{
  "$schema": "[https://opencode.ai/config.json](https://opencode.ai/config.json)",
  "share": "disabled"
}
```

---

## 🏗️ 2. Inicialização do Projeto

Para configurar os motores no repositório pela primeira vez (ou se as configurações de time sofrerem alterações):

1. **Inicialize a Governança (OpenSpec):**
   ```powershell
   openspec init
   ```
   *Certifique-se de que o arquivo `openspec/config.yaml` gerado contenha as instruções em inglês e a exigência de artefatos em português, além de detalhar a stack tecnológica (Java, Spring Boot, Arquitetura Hexagonal, Mensageria com Kafka, etc.).*

2. **Inicialize a Execução (OpenCode):**
   ```powershell
   opencode run "/init"
   ```
   *Isso criará o arquivo `AGENTS.md` para que a IA entenda o contexto geral do repositório.*

---

## 🔄 3. O Fluxo de Trabalho Diário (Ciclo de Vida da Feature)

Para cada nova funcionalidade (ex: criar um novo *producer* Kafka, adicionar um novo *Adapter*, criar uma rota REST), siga os 5 passos abaixo estritamente via terminal:

### Passo 1: Planejamento (Propose)
Use o CLI do OpenCode para invocar a criação das especificações de forma "headless" (sem abrir a interface gráfica).
```powershell
opencode run "/opsx:propose implementar envio de eventos de auditoria via Kafka usando KafkaTemplate"
```
* **Resultado:** A IA criará a pasta da mudança (ex: `openspec/changes/2026-04-07-auditoria-kafka/`) contendo o `proposal.md`, `specs/spec.md`, `design.md` e `tasks.md`.

### Passo 2: Auditoria Humana (Validate)
Não confie cegamente na arquitetura gerada. Valide estruturalmente os artefatos via terminal para garantir que nenhuma regra do `config.yaml` foi violada.
```powershell
openspec validate
```
*Revise o `design.md` e o `tasks.md` rapidamente. Certifique-se de que a Arquitetura Hexagonal está sendo respeitada.*

### Passo 3: Execução de Código (Apply)
Como o contexto do terminal é efêmero ao usar o CLI isolado, a IA focará 100% na geração de código a partir das tarefas aprovadas, evitando alucinações.
```powershell
opencode run "/opsx:apply"
```
* **Resultado:** O OpenCode criará/editará os arquivos `.java`, `.yml` ou testes necessários, e marcará os itens do `tasks.md` com `[x]`.

### Passo 4: Garantia de Qualidade (Verify)
Force a IA a auditar o próprio código recém-gerado, cruzando-o com os requisitos estabelecidos no `specs/spec.md`.
```powershell
opencode run "/opsx:verify"
```

### Passo 5: Fechamento e Limpeza (Archive)
Se a funcionalidade estiver testada e aprovada, consolide os novos requisitos na Fonte da Verdade do projeto (`openspec/specs/`) e arquive a área de trabalho atual.
```powershell
openspec archive
```
*Feito isso, o código e as especificações estão prontos para o `git commit` e a abertura do Pull Request.*

---

## 📌 Comandos de Bolso (Cheatsheet)

| Comando | O que faz? |
| :--- | :--- |
| `openspec view` | Abre um painel interativo no terminal para visualizar as specs atuais. |
| `openspec list` | Lista as mudanças que estão em andamento. |
| `openspec status` | Mostra o progresso dos artefatos (Proposal, Design, Tasks) da mudança atual. |
| `opencode run "/opsx:explore [dúvida]"` | Faz a IA analisar o código e sugerir abordagens arquiteturais sem criar arquivos. |
```

Este modelo está direto ao ponto e focado na operação. Você pode expandir as seções de contexto tecnológico diretamente no `config.yaml` da sua aplicação sempre que for preciso introduzir um padrão novo no ambiente de trabalho. Se precisar de mais algum ajuste ou comando específico para integrar em algum pipeline automatizado de CI/CD, é só falar!
