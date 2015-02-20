@echo on

set MC=unset
set MC=x86e_win64
:mc_done
:pdirset

set start_cmd=start ""
set PRO_DIRECTORY=C:\Program Files\PTC\Creo 2.0\Common Files\F001
set CREOAPP_DIRECTORY=C:\Program Files\PTC\Creo 2.0\Common Files\F001
set PTCPATH=true
:ptcpathset
	"C:\Program Files\PTC\Creo 2.0\Common Files\F001\x86e_win64\obJ\purge.exe" %1 %2 %3 %4 %5
	:ptc_end
	 %ERRORLEVEL%
