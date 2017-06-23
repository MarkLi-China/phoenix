:: 执行批处理前先要目录下创建font_properties文件

echo Run Tesseract for Training..
tesseract.exe pm.font.exp0.tif pm.font.exp0 -psm 7 nobatch box.train

echo Compute the Character Set..
unicharset_extractor.exe pm.font.exp0.box
mftraining -F font_properties -U unicharset -O pm.unicharset pm.font.exp0.tr

echo Clustering..
cntraining.exe pm.font.exp0.tr

echo Rename Files..
rename normproto pm.normproto
rename inttemp pm.inttemp
rename pffmtable pm.pffmtable
rename shapetable pm.shapetable

echo Create Tessdata..
combine_tessdata.exe pm.

echo. & pause