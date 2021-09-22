
FROM python:3.8

RUN mkdir /app && pip install pipenv

COPY /src/ /app/src/
#copia le dir del progetto dentro il contesto di build src assieme al file Pipfile

COPY Pipfile /app/

WORKDIR /app

CMD pipenv run python src/main.py
