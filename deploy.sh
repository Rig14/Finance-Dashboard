#!/bin/bash
set -e

SERVER=root@finance.rivis.ee

./gradlew test jar --info
(cd ui && npm run test:run && npm run check && npm run build)

DOCKER_DEFAULT_PLATFORM=linux/amd64 docker compose build

docker save finance-dashboard | gzip | ssh $SERVER 'gunzip | docker load'

scp compose.yml compose.server.yml $SERVER:finance-dashboard/
ssh $SERVER << END
  cd finance-dashboard && \
  mkdir -p logs && \
  docker compose logs -t app > logs/app-$(date --iso-8601=minutes).log && \
  docker compose -f compose.yml -f compose.server.yml up -d --wait
END
