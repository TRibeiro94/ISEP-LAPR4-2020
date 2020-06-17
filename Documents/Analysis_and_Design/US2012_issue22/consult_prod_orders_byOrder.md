# US2012_issue22 - Consultar as ordens de produção de uma dada encomenda
=======================================


# 1. Requisitos

**US2012** Como Gestor de Produção, eu pretendo consultar as ordens de produção de uma dada encomenda.

A interpretação feita deste requisito foi no sentido de criar uma forma de se consultar toda a informação de uma determinada ordem de produção tendo por base um código de uma determinada encomenda.


# 2. Análise

Durante a execução do programa é importante termos a opção de podermos consultar a informação de uma determinada ordem de produção, fornecendo apenas o código da encomenda, já que este se trata de um *ValueObject* da *Entity* ordem de produção.


# 3. Design

A forma encontrada para resolver este problema foi criar uma classe **ProductionOrder** a qual contém, entre diversos atributos, o código da encomenda à qual se encontra associado.
Assim sendo, através da classe **ProductionOrderController** iremos aceder à classe **ProductionOrder** de modo a rastrear qual/quais as ordens de execução que devem ser mostradas, tendo por baso o código da encomenda fornecido. Já que a classe **ProductionOrder** deverá aceder ao **ProductionOrderRepository** para extrair a informação requerida.

## 3.1. Realização da Funcionalidade

![Check_production_order_by_request](US2012_issue22.png)

## * 3.2. Diagrama de Classes

![Check_production_order_by_request](...)

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
