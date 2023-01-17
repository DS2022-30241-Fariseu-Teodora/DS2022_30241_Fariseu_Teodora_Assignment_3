FROM node AS builder

WORKDIR /app

COPY *.json ./
COPY babel.config.js ./
RUN npm install

COPY src ./src
COPY public ./public
RUN npm run build

FROM nginx
COPY nginx/nginx.conf /etc/nginx/nginx.conf 
COPY nginx/mime.types /conf/mime.types
COPY --from=builder /app/dist /static

COPY src/proto/chat_pb.js ./src/proto/chat_pb.js
COPY src/proto/chat_grpc_web_pb.js ./src/proto/chat_grpc_web_pb.js

FROM envoyproxy/envoy-dev:latest
COPY envoy.yaml /etc/envoy/envoy.yaml
RUN chmod go+r /etc/envoy/envoy.yaml