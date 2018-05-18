FROM mongo

COPY init.json /init.json
CMD mongoimport --host mongo --db finite_type_service --collection finiteType --type json --file /init.json --jsonArray