FROM node:18-alpine

WORKDIR /front

EXPOSE 3000

COPY ["package.json", "package.json*", "./"]

RUN yarn install

COPY . .

CMD ["yarn", "start"]
