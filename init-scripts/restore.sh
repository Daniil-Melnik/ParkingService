#!/bin/bash

psql -U admin -d parking -f /docker-entrypoint-initdb.d/parking_backup.sql