  for f in *.zip_dir ; do 
  	cd $f
  	echo ------$f;

	echo ....==================

	cat src/main/resources/static/index.html | grep "button"
	cat src/main/resources/static/app.js | grep "function" -A 1 -B 10
	cat src/main/resources/static/app.js | grep "POST" -A 1 -B 10
	cat src/main/resources/static/app.js | grep "PUT" -A 1 -B 10

	echo ....==================


	cat src/main/resources/static/app.js | grep "stompClient.subscribe(" -A 3 -B 3
	cat src/main/resources/static/app.js | grep "stompClient.send(" -A 3 -B 3

	cat src/main/java/edu/eci/arsw/msgbroker/GamesResourceController.java  | grep "convertAndSend" -A 3 -B 3
	cat src/main/java/edu/eci/arsw/msgbroker/GamesResourceController.java  | grep "convertAndSend" -A 3 -B 3
	cat src/main/java/edu/eci/arsw/msgbroker/GamesResourceController.java  | grep "@RequestMapping" -A 2 -B 2

	echo ....==================

  	echo *******$f;
  	cd ../;  
  done
