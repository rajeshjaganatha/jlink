@echo on
for /d /r "D:\Proe_RnD\jlink\jlinkapp\media\" %%f in (*) do echo F| XCOPY "D:\Proe_RnD\jlink\jlinkapp\purge.bat" "%%f" & pushd "%%f" & Call "purge.bat" & del purge.bat & popd & echo "%%f"
exit
 