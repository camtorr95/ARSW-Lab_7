  for f in *.zip_dir ; do 
  	cd $f
  	echo ------$f;

	cat src/main/resources/static/app.js | grep "+gameid+" -A 1 -B 10

  	echo *******$f;
  	cd ../;  
  done
