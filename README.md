<h1>🚀 Projeto Selenium Java BDD + Page Objects +  JUnit 5</h1>

Código limpo e eficiente com Selenium WebDriver utilizando Java. Um framework leve e flexível para automação de testes em aplicações web. Abaixo estão algumas das principais características:

✅ Estrutura modular para facilitar a manutenção e escalabilidade

📖 Implementa o padrão Page Object Model (POM) para legibilidade e reutilização de código

💬 Integração com metodologia BDD (Behavior-Driven Development) utilizando Gherkin para especificação de testes colaborativos

🚀 Suporte multi-browser para a realização de testes eficientes
  
  
## 💻 Estrutura do Projeto

O projeto Selenium Java é organizado em torno de nove principais conjuntos de código-fonte para garantir a eficiência dos testes automatizados:

1. 🏃‍♂️ **Runner**: A classe `RunnerTest` define os critérios dos testes que são executados e como os resultados são reportados.
2. 🔄 **Hooks**: A classe `Hooks` contém métodos de configuração e finalização que são executados antes e depois de cada cenário de teste respectivamente. Estes métodos permitem a preparação e limpeza do ambiente de testes.
3. 📜 **Features**: O arquivo `Login.feature` contém as especificações no formato Given-When-Then, parte da estratégia BDD descrevendo o comportamento esperado durante o processo de login.
4.  📝 **Steps**: A classe `LoginSteps` transforma as descrições contidas em `Login.feature` em ações reais realizadas pelo WebDriver.
5. 🔄 **Interaction**: A lógica das interações do usuário na página de login é realizada pela classe `LoginInteraction` utilizando Selenium WebDriver para simular ações do usuário.
6. 📄 **Pages**: A classe `LoginPage` segue o padrão Page Object, isolando as interações da página para tornar os testes mais robustos e fáceis de manter.
7. 🛠️ **Utils**: A classe `Driver` atua como uma classe auxiliar para operações relacionadas ao driver do Selenium.
8. 🔧 DriverConfigurator: A classe DriverConfigurator é responsável pela configuração dos drivers dos navegadores utilizados no framework Selenium WebDriver, permitindo a personalização precisa dos drivers.
9. 🚘 Driver: A classe Driver é a responsável por gerir a inicialização e fechamento dos drivers dos navegadores utilizados para os testes. Ela também permite a seleção do navegador a ser utilizado (Chrome, Firefox, etc.).

O projeto inclui um arquivo `pom.xml`, típico de projetos Maven, que contém informações e detalhes de configuração, incluindo dependências. A hierarquia do projeto segue padrões recomendados para testes automatizados Selenium.

  

Ideal para equipes de QA que buscam agilidade e qualidade na automação de testes web.
  

## 📊 Implementação do Allure Report com Evidências

O projeto inclui a implementação do Allure Report, que permite a geração de relatórios detalhados e visuais dos testes executados. O Allure captura evidências, como screenshots, durante a execução dos testes, facilitando a análise de falhas e a documentação dos resultados.

- **Captura de Screenshots**: Durante a execução dos testes, screenshots são automaticamente anexados aos relatórios do Allure, proporcionando uma visão clara do estado da aplicação em cada etapa do teste.
- **Relatórios Interativos**: Os relatórios gerados são interativos e fáceis de navegar, permitindo que as equipes de QA visualizem rapidamente os resultados dos testes e identifiquem problemas.
- **Integração Simples**: A configuração do Allure é simples e pode ser facilmente integrada ao seu projeto Maven, garantindo que você tenha acesso a relatórios de qualidade sem complicações.


O Allure Report é uma ferramenta poderosa para gerar relatórios de testes automatizados, proporcionando uma visualização clara e interativa dos resultados dos testes. Abaixo estão os passos e considerações para integrar e utilizar o Allure Report em seu projeto Selenium Java.

#### 1. 🏃‍♂️ Execução do TestRunner
Para garantir que o Allure Report seja gerado corretamente, é necessário executar os testes a partir da classe `TestRunner`. Isso assegura que todas as informações relevantes dos testes sejam coletadas e armazenadas nos resultados do Allure.

#### 2. 📄 Geração do Relatório
Após a execução dos testes, você pode gerar o relatório do Allure utilizando o seguinte comando no terminal:

```bash
allure serve report PATH/allure-results
```

Para gerar o arquivo HTML do report basta executar no terminal o comando abaixo:
```bash
allure generate allure-results --clean -o allure-report
```


## 📝 Visão Geral 

Este projeto contém métodos utilitários projetados para tornar os testes com Selenium WebDriver mais suaves e eficientes. A classe de utilitários estende a classe Driver, fornecendo métodos de conveniência para ações comuns do WebDriver.

### 💡 Recursos 

- Mecanismo de Tentativa 🔄: Método para realizar ações com tentativas (retries). Ideal para lidar quando as ações do WebDriver falham devido a questões de tempo.
- Método Abrangente de Ação em Elementos 🎯: Método único para realizar operações de clique, enviar chaves e selecionar. Reduz o código repetitivo.
- Mecanismo de Log 📝: Cada operação é registrada, facilitando a depuração.
- Espera pela Visibilidade dos Elementos ⏱️: Métodos para aguardar até que um elemento esteja visível. Economiza tempo e reduz testes inconsistentes.
</html>

## Licença
Copyright (c) 2025 Michael Alba.
Todos os direitos reservados.
