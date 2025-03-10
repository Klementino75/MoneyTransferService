FROM node:12
WORKDIR /app
ADD package*.json ./
RUN npm install
COPY . .
EXPOSE 5500
ENTRYPOINT ["npm", "run", "start"]