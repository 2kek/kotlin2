import java.io.File

fun main() {
        // Пути к исходному файлу и файлу результата
        val source = File("C:\\Users\\2kek\\Desktop\\KotlinLab2\\src\\main\\Task\\task.txt")
        val result = File("C:\\Users\\2kek\\Desktop\\KotlinLab2\\src\\main\\Task\\resultat.txt")

        // Выводим меню
        println("Вы попали в меню, выберите действие:")
        println("1) С помощью Input- и OutputStream\n" +
                "2) С помощью встроенных методов Kotlin\n" +
                "3) С помощью BufferedReader и BufferedWriter")
        println("Введите номер: ")
        val x = readLine()?.toIntOrNull() ?: return

        // Выбираем нужную функцию
        when (x) {
                1 -> readWriteInputOutputStream(source, result)
                2 -> readWriteKotlin(source, result)
                3 -> readWriteBuffered(source, result)
                else -> println("Неверный ввод")
        }

        // Создаем новую папку, если она еще не создана
        val resultFolder = File("C:\\Users\\2kek\\Desktop\\KotlinLab2\\src\\main\\folderResult")
        if (resultFolder.mkdir()) {
                println("Создал новую папку folderResult")
        } else {
                println("Папка уже существует, перемещаю в директорию выше")
        }

        // Перемещаем файл в папку folderResult
        val finalResult = File(resultFolder, "final_resultat.txt")
        result.copyTo(finalResult, overwrite = true)
        println("Файл перенесен в другую папку")

        // Переименовываем файл
        finalResult.renameTo(File(resultFolder, "final_result.txt"))
        println("Файл переименован")

        println("End")
}

// Чтение и запись с использованием Input- и OutputStream
fun readWriteInputOutputStream(source: File, result: File) {
        source.inputStream().use { inputStream ->
                result.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                }
        }
}

// Чтение и запись с использованием встроенных методов Kotlin
fun readWriteKotlin(source: File, result: File) {
        result.writeText(source.readText().replace(Regex("\\s+"), " "))
}

// Чтение и запись с использованием BufferedReader и BufferedWriter
fun readWriteBuffered(source: File, result: File) {
        source.bufferedReader().use { reader ->
                result.bufferedWriter().use { writer ->
                        reader.lineSequence().forEach {
                                writer.write(it.trim().replace("\\s+".toRegex(), " "))
                                writer.newLine()
                        }
                }
        }
}
