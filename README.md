# Embeddecy
Язык Embeddecy является высокоуровневым языком программирования микроконтроллеров, представляет собой надстройку над языком С. 
На языке Embeddecy описываются программы для программируемых устройств в системе [MCU Blocks](http://mcublocks.com).

## Быстрый старт
Для использования генератора требуется скачать папку `Embeddecy-project/quickstart.zip` и распаковать ее. В папке находится:
* `embc.jar` - собственно, сам транслятор;
* `testproj` - тестовый проект для CoIDE v1.7.8 для платы STM32F3Discovery, с подключенной ОС FreeRTOS v9.0.0;

Для компиляции C-файла потребуется компилятор `GNU Tools ARM Embedded`,его можно скачать [здесь](https://developer.arm.com/open-source/gnu-toolchain/gnu-rm/downloads). Также в системе должен быть установлен JDK. После распаковки требуется открыть командную строку и перейти в текущую директорию. Запуск транслятора осуществляется следующей командой:

```bash
java -jar embc.jar -file <путь к файлу *.embc> -pathtocompiler <путь к компилятору>
```

Пример команды:

```bash
java -jar embс.jar -file "res/event-test/eventtest3.embc" -pathtocompiler "GNU Tools ARM Embedded/62017-q1-update/bin/arm-none-eabi-gcc"
```

В результате выполнения команды в текущей директории появится файл `_gen_c_file.c`, в котором будет находиться готовая к компиляции программа на языке С. Далее требуется содержимое этого файла полностью скопировать и заменить содержимое файла `main.c` проекта CoIDE. После этого можно по нажатию кнопкок Ctrl + R пересобрать проект и по нажатии кнопки `Download To Flash` загрузить новую прошивку в микроконтроллер.

## Использование исходников

Для корректной работы потребуется:
- `Eclipse Luna IDE for Java and DSL Developers`;
- `ANTlR 4 SDK` - плагин для Eclipse;
- Последняя версия `antlr-4.7-complete.jar`. Для использования новой версии требуется зайти в меню `Windows ->Preferences`, выбрать `ANTLR 4 -> Tool` и указать путь к jar-файлу. 

Для работы с проектом его требуется сперва импортировать, как обычный Eclipse-проект. Далее указать в конфигурации запуска `Run -> Run Configurations` необходимые параметры командной строки и запустить по нажатию на кнопку `Run`. В качестве главного класса требуется выбрать `EmbeddecyRunner.java`.

## Полезные ссылки

- [Страница проекта](http://mcublocks.com/embeddecy-ide/)
- [Eclipse Luna IDE](https://www.eclipse.org/downloads/packages/release/luna/sr2)
- [ANTLR4 IDE Plugin](http://www.antlr.org/tools.html)
- [Установка плагина ANTLR4 IDE в Eclipse](https://github.com/antlr4ide/antlr4ide)
- [Документация](http://mcublocks.com/embeddecy-ide/docs/)