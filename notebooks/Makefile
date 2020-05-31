hadoop_up:
	cd docker && docker-compose -f hadoop-compose.yml up
	cd ..

hadoop_start:
	cd docker && docker-compose -f hadoop-compose.yml start
	cd ..

hadoop_stop:
	cd docker && docker-compose -f hadoop-compose.yml stop
	cd ..


hadoop_down:
	cd docker && docker-compose -f hadoop-compose.yml down
	cd ..


spark_up:
	cd docker && docker-compose -f spark-compose.yml up
	cd ..


spark_start:
	cd docker && docker-compose -f spark-compose.yml start
	cd ..


spark_stop:
	cd docker && docker-compose -f spark-compose.yml stop
	cd ..


spark_down:
	cd docker && docker-compose -f spark-compose.yml down
	cd ..

spark_notebook:
	docker run -it --rm -v ${CURDIR}/docs:/home/jovyan/work -e GRANT_SUDO=yes -p 8888:8888 -p 4040:4040 almondsh/almond

	