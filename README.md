# 🐾 Sistema de Adoção de Pets (CLI)

Sistema de gestão para adoção de animais desenvolvido em Java, focado em
boas práticas de Programação Orientada a Objetos e arquitetura em
camadas.

Este projeto foi desenvolvido como solução para o desafio proposto por
**Lucas Carrilho de Almeida**.

## 📋 Sobre o Projeto

O objetivo foi criar uma aplicação via terminal que simula um
sistema real de gestão.\
O diferencial desta implementação é a arquitetura robusta e a
independência de frameworks, utilizando **Java puro** para resolver
problemas de persistência de dados e regras de negócio.

## 🚀 Funcionalidades

-   **Cadastro de Pets:** Registo completo com validação de regras de negócio (idade, peso,
    nome).

-   **Persistência de Dados:** Os dados iniciais são salvos em ficheiros locais (.txt), simulando uma base
    de dados.

-   **Edição e Remoção:** Atualização de dados cadastrais e remoção de registos mantidos em memória durante a execução.

-   **Busca Avançada**

    -   **Modo Estrito:** Filtragem obrigatória por Tipo (Cão/Gato).
    -   **Modo Dinâmico:** Busca flexível por qualquer critério (Nome,
        Raça, Peso, etc.).

-   **Listagem Geral:** Visualização de todos os animais cadastrados.

## 🛠️ Tecnologias e Conceitos Aplicados

-   **Java (JDK)** -- Linguagem principal
-   **Arquitetura MVC (Model-View-Controller)**
    -   `view`: Interação com o utilizador
    -   `service`: Regras de negócio e orquestração
    -   `repository`: Acesso e manipulação de dados
    -   `model`: Entidades
-   **Java IO** -- Leitura e escrita de ficheiros
-   **Tratamento de Exceções** -- Exceções personalizadas
    (`DomainException`)
-   **Clean Code & SRP** -- Código coeso, desacoplado e organizado

## 📂 Estrutura do Projeto

    src/
    ├── exceptions/      # Exceções personalizadas (DomainException)
    ├── model/           # Entidades (Pet) e Enums (PetType, PetSex)
    ├── repository/      # Camada de acesso a dados (PetRepository)
    ├── service/         # Lógica de negócio (ServiceAdoption)
    ├── util/            # Utilitários (FileUtils, InputUtils, PetValidator)
    └── view/            # Menus e interação com o utilizador (MenuView)

## ⚙️ Como Executar

### Pré-requisitos

-   Java JDK 11 ou superior instalado

### Passos

1.  Clone este repositório:

``` bash
git clone https://github.com/ghosantos/pet-adoption-challenge
```

2.  Aceda à pasta do projeto:

``` bash
cd pet-adoption-challenge
```

3.  Compile e execute a classe `Main`.

## 🧠 Destaques da Implementação

### Validações

O sistema não aceita dados inconsistentes.\
A classe `PetValidator` centraliza regras como:

-   Idade não pode ser negativa ou irreal
-   Nomes devem conter caracteres válidos
-   Endereços devem seguir um formato legível

### Gestão de Dados

O sistema simula um fluxo híbrido para fins de estudo:

-   **Cadastro Inicial:** Persistência física em arquivos `.txt`
-   **Manipulação:** Operações realizadas em memória via Repository

## 📝 Autor

Desenvolvido por **Gustavo Oliveira**

🔗 [LinkedIn](https://www.linkedin.com/in/gustavo-oliveira-1477922b3/)\
🔗 [GitHub](https://github.com/ghosantos)
