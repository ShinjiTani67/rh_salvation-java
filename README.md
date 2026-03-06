# Rh_Salvation

## Índice 

- [Integrantes]
- [Descrição]
- [Back-end]
- [Video]
- [Deploy]
- [Troubleshooting]

## Integrantes  
- Fernando Shinji Tanigushi RM553587

## Descrição
Rh_Salvation é um sistema desenvolvido para apoiar a gestão estratégica de pessoas, 
auxiliando o setor de Recursos Humanos na realocação de colaboradores que apresentem dificuldades diante das rápidas transformações tecnológicas atuais.
O sistema permite que o RH visualize a função exercida por cada funcionário e identifique possíveis necessidades de realocação, buscando manter o colaborador ativo e adequado às demandas da empresa.
Por outro lado, os funcionários podem acessar a plataforma por meio de login próprio, 
possibilitando que consultem vagas internas compatíveis com seu perfil e interesses profissionais. Dessa forma, o Rh_Salvation promove um processo interno de mobilidade e desenvolvimento, 
contribuindo tanto para o crescimento dos colaboradores quanto para a eficiência organizacional.

## Front-end
- https://github.com/ShinjiTani67/rh_salvation--web-react.git

## Video

- https://youtu.be/kTeoWnb18W4

## Deploy
- Certifique-se de que o Docker e o Docker Compose estejam instalados na máquina.
- No diretório do projeto, execute o comando abaixo para construir e iniciar os containers:
bash
docker-compose up --build
- Após a inicialização dos containers, a aplicação poderá ser acessada no navegador pelo endereço:
http://localhost:8083

## Troubleshooting

- Caso ocorram problemas durante a execução do projeto, algumas verificações podem ser realizadas:
- Verificar se os containers estão em execução:
  docker ps
- Verificar os logs dos containers:
  docker logs rh-api
  docker logs rh-postgres
- O projeto possui um Healthcheck configurado para o PostgreSQL, que garante que a API Java só será iniciada após o banco de dados estar disponível.
- Caso algum erro ocorra na inicialização, recomenda-se reconstruir os containers:
  docker-compose down
  docker-compose up --build
