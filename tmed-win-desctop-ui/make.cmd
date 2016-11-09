call mvn clean install -Dmaven.test.skip=true
call javapackager.exe -createjar -nocss2bin -appclass com.airplaneSoft.translateMeDude.winApp.App -srcdir target/classes -outdir target/packager_jar -outfile tmed.jar -v -manifestAttrs "Specification-Version=1.0,Implementation-Version=SNAPSHOT,Specification-Title=TranslateMe Dude App"
call javapackager.exe -deploy -srcdir target/packager_jar -outdir target/production -outfile tmed -width 800 -height 600 -name "tmed" -appclass com.airplaneSoft.translateMeDude.winApp.App -native -title "tmed" -vendor "com.airplaneSoft" -description "tmed" -v
pause