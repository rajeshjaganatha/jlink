﻿drawing_setup_file $PRO_DIRECTORY\text\prodetail.dtl
format_setup_file $PRO_DIRECTORY\text\prodetail.dtl
pro_unit_length unit_inch
pro_unit_mass unit_pound
template_designasm $PRO_DIRECTORY\templates\inlbs_asm_design.asm
template_drawing $PRO_DIRECTORY\templates\c_drawing.drw
template_sheetmetalpart $PRO_DIRECTORY\templates\inlbs_part_sheetmetal.prt
template_solidpart $PRO_DIRECTORY\templates\inlbs_part_solid.prt
todays_date_note_format %Mmm-%dd-%yy
tolerance_standard ansi
weld_ui_standard ansi
regen_failure_handling resolve_mode
web_browser_homepage D:\Proe_RnD\jlink\jlinkapp
terminal_command C:\Windows\system32\cmd.exe
save_objects all
