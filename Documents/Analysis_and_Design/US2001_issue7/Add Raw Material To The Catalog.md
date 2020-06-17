# US2001_issue7 - Add Raw Material To The Catalog
=======================================


# 1. Requisitos

**US2001** Como Gestor de Produção, eu pretendo adicionar uma matéria-prima ao catálogo.

A interpretação feita deste requisito foi no sentido de permitir a adição de uma matéria-prima específica ao catálogo já existente.


# 2. Análise

A qualquer momento pode ser relevante a adição de uma matéria-prima ao catálogo de matérias-primas já presentes na base de dados.


# 3. Design

A forma encontrada para resolver este problema foi através do uso da class **RegisterRawMaterialAction** em conjunto com a class **RegisterRawMaterialUI** de forma a garantir as regras de negócio dadas pelo cliente, permitindo assim a adição de uma matéria-prima à base de dados, ou seja, ao **RawMaterialRepository**.

## 3.1. Realização da Funcionalidade

![Add Raw Material To The Catalog](US2001_issue7.png)

## * 3.2. Diagrama de Classes

![Add Raw Material To The Catalog](...)

## 3.3. Padrões Aplicados

* *Controller*

## * 3.4. Testes

*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*


# * 4. Implementação

As classes **RawMaterialPrinter**, **RegisterRawMaterialAction** e **RegisterRawMaterialUI** encontra-se inserida no Package *package eapli.base.app.backoffice.console.presentation.rawmaterials* e tais classes irão dar acesso, através da **MainMenu** de modo a se inserir uma Matéria-prima na base de dados.


# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*


# 6. Observações

Nada a registar.
