package xyz.devura.androidstorageio.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import xyz.devura.androidstorageio.app.MyApplication
import java.io.*
import java.lang.Exception

object FileRepository {

    private val TAG = FileRepository::class.java.simpleName

    private val applicationContext = MyApplication.getApplicationContext()

    fun saveTextFile(content: String, fileName: String): Boolean {
        return try {
            getFile(fileName).bufferedWriter().use { writer ->
                writer.write(content)
            }
            true
        } catch (exception: IOException) {
            Log.d(TAG, exception.message ?: "Error saving file $fileName")
            false
        }
    }

    fun readTextFile(fileName: String): String? {
        return try {
            getFile(fileName).bufferedReader().readText()
        } catch (exception: FileNotFoundException) {
            Log.d(TAG, exception.message ?: "$fileName not found in directory ${getStorageLocation()}")
            null
        } catch (exception: IOException) {
            Log.d(TAG, exception.message ?: "Error saving file $fileName")
            null
        }
    }

    fun saveImageFile(source: Int, filename: String): Boolean {
        val fos = FileOutputStream(getFile(filename))
        val bos = BufferedOutputStream(fos)
        return try {
            val bitmap = BitmapFactory.decodeResource(applicationContext.resources, source)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
            true
        } catch (exception: IOException) {
            Log.d(TAG, exception.message ?: "Error saving file $filename")
            false
        } finally {
            bos.close()
        }
    }

    fun serializeObject(objectToWrite: Serializable, filename: String): Boolean {
        val fos = FileOutputStream(getFile(filename))
        val bos = BufferedOutputStream(fos)
        val oos = ObjectOutputStream(bos)
        return try {
            oos.writeObject(objectToWrite)
            true
        } catch (exception: IOException) {
            Log.d(TAG, exception.message ?: "Error serializing object")
            false
        } finally {
            oos.close()
        }

    }

    fun deserializeObject(filename: String): Any {
        val fis = FileInputStream(getFile(filename))
        val bis = BufferedInputStream(fis)
        val ois = ObjectInputStream(bis)
        return try {
            return ois.readObject()
        } catch (exception: IOException) {
            Log.d(TAG, exception.message ?: "Error deserializing object")
            false
        } finally {
            ois.close()
        }
    }

    private fun getFile(fileName: String): File =
        File(getStorageLocation(), fileName)

    private fun getStorageLocation(): File = applicationContext.filesDir

}