package com.tijun.storagetest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import java.io.File
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
private const val TAG = "FirstFragment"

class FirstFragment : Fragment() {

    val filename = "storestream"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_first.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        storeFiles()

        storeOutputStream()

        storeInputStream()

        viewFileList()
    }


    /**
     * FileAPI来访问和存储文件
     */
    private fun storeFiles() {
        val filename = "storefiles"
        val file = File(context?.filesDir, filename)
    }

    /**
     * 使用流存储文件
     */
    private fun storeOutputStream() {

        val fileContents = "Hello world!"
        context?.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it?.write(fileContents.toByteArray())
        }
    }

    /**
     * 使用流读取文件
     */
    private fun storeInputStream() {
        context?.openFileInput(filename)?.bufferedReader()?.useLines { lines ->

            var fold = lines.fold("test") { some, text ->
                "$some + $text"
            }
            Log.d(TAG, "storeInputStream() called with: lines1 = $fold")
        }
    }

    /**
     * 查看文件列表
     */
    private fun viewFileList() {
        var filelist: Array<String>? = context?.fileList()
        Log.d(TAG, "viewFileList() called${Arrays.toString(filelist)}")
    }
}