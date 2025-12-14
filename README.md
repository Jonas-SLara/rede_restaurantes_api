# cronograma_api
api geral para cronograma de eventos e atividades para o front

```bash
cp .env.example .env
export $(grep -v '^#' .env | xargs)
```

caso nao tenha o wrapper do mvn, executar comando, com o mvn instalado (opcional)

```bash
mvn -N wrapper:wrapper
```

executando no modo dev (precisa das variaveis de ambiente e wrapper acima)
```bash
./mvnw spring-boot:run
```