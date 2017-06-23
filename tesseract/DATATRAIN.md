## tesseract样本训练

*工具：jTessBoxEditor*

+ 用jTessBoxEditor合并tif，命名为*lang*.font.exp0.tif

+ 生成BOX，使用命令：

   >tesseract *lang*.font.exp0.tif *lang*.font.exp0 batch.nochop makebox

   可能会出现

   >empty page!

   加上参数-psm 7

   >tesseract *lang*.font.exp0.tif *lang*.font.exp0 -psm 7 batch.nochop makebox;

+ 使用jTessBoxEditor矫正错误的字符

+ 将font_properties复制到训练的目录下

+ 将train.bat复制到训练的目录下，将train.bat里的pm更改为*lang*，执行train.bat

+ 将*lang*.traineddata放入tesseract/traineddata下