
FROM node

RUN mkdir /app

COPY /src/ /app/src/
#copia le dir del progetto dentro il contesto di build src assieme al file package.json

COPY package.json /app/

WORKDIR /app

RUN npm install

CMD npm run start
