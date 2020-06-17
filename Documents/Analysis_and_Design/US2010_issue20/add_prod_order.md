# US2010_issue20 - Introduzir manualmente uma nova ordem de produção
=======================================


# 1. Requisitos

**US2010** Como Gestor de Produção, eu pretendo introduzir manualmente uma nova ordem de produção.

A interpretação feita deste requisito foi no sentido de criar uma forma de carregar para a base de dados toda a informação referente a uma ordem de produção, introduzindo cada um dos dados necessários de forma manual.


# 2. Análise

Durante a execução do programa é importante termos a opção de podermos introduir manualmente um novo registo de uma ordem de produção, adicionando-a à base de dados existente.


# 3. Design

A forma encontrada para resolver este problema foi criar uma classe **ProductionOrder** a qual é referente a um **Product** em particular.
Assim sendo, através da classe **ProductionOrderController** iremos aceder à classe **Product** de modo a verificar se o produto, relativo ao qual pretendemos introduzir uma ordem de produção, já existe, e se tal não se verificar, a classe **ProductionOrder** irá ser chamada e consecutivamente serão pedidos os restantes elementos referentes a essa nova ordem de produção, sendo esta adicionada ao **ProductionOrderRepository**.

## 3.1. Realização da Funcionalidade

![New_production_order](US2010_issue20.png)

## 3.2. Diagrama de Classes

![New_production_order](US2010_issue20_CD.png)

## 3.3. Padrões Aplicados

* *Controller*

## * 3.4. Testes

*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1:** Verificar que não é possível criar uma instância da classe Exemplo com valores nulos.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}


# * 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*


# * 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*


# * 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*
