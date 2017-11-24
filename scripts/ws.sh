  for f in *.zip_dir ; do 
  	cd $f
  	echo ------$f;
	mvn spring-boot:run 
	echo PID:::::::: $!
	read -p "TERMINADO $f "
	kill -9 $!	
  	echo *******$f;
  	cd ../;  
  done
