FROM 10.10.63.97:8083/git-alpine:2.49.1 AS git-clone

ARG FEAPP_GIT_URL
ARG FEAPP_GIT_BRANCH

WORKDIR /artifacts
RUN git clone -b ${FEAPP_GIT_BRANCH} ${FEAPP_GIT_URL} /artifacts

FROM 10.10.63.97:8083/node:20-alpine AS node-build

COPY --from=git-clone /artifacts /artifacts
WORKDIR /artifacts

RUN npm install --force
RUN npm run build

FROM 10.10.63.97:8083/openresty:1.27.1.2-alpine-slim

COPY --from=node-build /artifacts/dist /usr/local/openresty/nginx/html

EXPOSE 80

RUN ln -s /usr/local/openresty/nginx /nginx
