# KNN регрессия

Данный проект представляет собой готовую KNN регрессию, написанную на Java для одного из задания для Вуза.
Проект содержит файлы:
* **KNN.java** - файл содержит сам KNN класс и вспомогательные для него функции
* **Preparing.java** - в файле находится класс Preparing, содержащий методы для вычисления среднего и стандартного отклонения, нормализации и денормализации данных.
* **ReadData.java** - файл содержит класс ReadData для считывания данных из csv файла, с последующим сохранением в два массива. table, для данных, которые не используются в регрессии. table_target, для данных, которые используются в регрессии.
* **WriteData.java** - файл содержит класс WriteData для записи данных в csv файл.
* **Main.java** - в файле представлен пример использования KNN регрессии с использованием нормализации и денормализации.
* **Data_2_2.csv** - файл, в котором хранится больше миллиона значений с данными по гидрологическим постам и автоматическим станциям Республики Башкортостан. Он используется для примера работы регрессии.

В примере работы KNN регрессия используется для восстановления данных по столбцам Температура воздуха, Атмосферное давление, Скорость ветра, Толщина снежного покрова и Количество осадков. 
