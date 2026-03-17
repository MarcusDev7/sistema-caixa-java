Sistema de Caixa e Controle de Estoque em Java

Projeto desenvolvido com o objetivo de praticar conceitos fundamentais de backend utilizando Java puro.

O sistema simula um caixa comercial rodando no console, permitindo cadastro de produtos, controle de estoque, realização de vendas e persistência de dados em arquivos CSV através de uma camada de repository.

Funcionalidades

Cadastro e listagem de produtos

Controle de entrada e baixa de estoque

Realização de vendas com múltiplos itens

Cálculo automático de subtotal e total

Seleção de forma de pagamento

Geração de comprovante de venda

Histórico persistido das vendas

Arquitetura

O projeto foi estruturado em camadas:

domain → entidades do sistema

service → regras de negócio

repository → persistência em arquivos CSV

ui → interação via console

Objetivo

Construir base sólida em lógica de negócio e organização de sistemas backend antes da evolução para projetos com Spring Boot e banco de dados relacional.