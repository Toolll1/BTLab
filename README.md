# BTLab
Веб-приложение для учета отсутствия сотрудников на рабочем месте


<h5>
ER-диаграмма:
</h5>

![This is an image](https://i.postimg.cc/7Lt7FBdc/er.jpg)

Для целей тестирования в БД добавлены сотрудники с id1 и id2;<br>
У сотрудника с id1 добавлена информация об отпуске;<br>
Заполнена БД с причинами отсутствия сотрудников: 1- Отпуск; 2 - Больничный, 3 - Прогул

<h5>
Инструкция по развертыванию:
</h5>
1. Откройте командную строку или терминал на вашем компьютере;<br>
2. Перейдите в каталог, в который вы хотите склонировать проект (cd <название каталога>); <br>
3. Введите следующую команду, чтобы склонировать проект с GitHub: git clone https://github.com/Toolll1/BTLab.git ;<br>
4. Перейдите в каталог проекта (cd <название проекта>);<br>
5. В командной строке или терминале запустите следующую команду, чтобы запустить проект: docker-compose up ;<br>
6. Когда все контейнеры успешно запустятся, вы увидите вывод в командной строке или терминале;<br>
7. Веб-интерфейс будет доступен по адресу `http://localhost:9999`.


