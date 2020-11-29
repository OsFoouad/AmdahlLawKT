package com.example.amdahllaw

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*

val types = arrayOf("Symmetric", "Asymmetric", "Dynamic")
const val n = 256.0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //spinner adapter
        val myadapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types)
        amdType.adapter = myadapter

        graphbtn.setOnClickListener {
            //make sure no graphs on the Grid
            graph.removeAllSeries()
            // determine which type of Amdahl's law we work on
            when (amdType.selectedItem) {
                "Symmetric" -> {
                    symmetricGraphh()
                }
                "Asymmetric" -> {
                    asymmetricGraphh()
                }
                "Dynamic" -> {
                    dynamicGraphh()
                }
            }
        }
    }

    private fun symmetricGraphh() {
        val amdSym1 = LineGraphSeries<DataPoint>()
        amdSym1.title = "f=.5"
        val amdSym2 = LineGraphSeries<DataPoint>()
        amdSym2.title = "f=.9"
        val amdSym3 = LineGraphSeries<DataPoint>()
        amdSym3.title = "f=.975"
        try {
            for (x in 1..300 step 30) {
                // at r = 256
                var f = 0.5
                amdSym1.appendData(
                    DataPoint(x.toDouble(), symmetricY(x.toDouble(), f)),
                    true,
                    100
                )
                //at r = 32
                f = .9
                amdSym2.appendData(
                    DataPoint(x.toDouble(), symmetricY(x.toDouble(), f)),
                    true,
                    100
                )
                //at r = 16
                f = .975
                amdSym3.appendData(
                    DataPoint(x.toDouble(), symmetricY(x.toDouble(), f)),
                    true,
                    100
                )
            }
            graph.addSeries(amdSym1)
            amdSym2.color = Color.GREEN
            graph.addSeries(amdSym2)
            amdSym3.color = Color.RED
            graph.addSeries(amdSym3)
            initialGraph()
            Toast.makeText(this, "Symmetric Graph Done", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
        }
    } // end of symmetric function

    private fun asymmetricGraphh() {
        val amdSym1 = LineGraphSeries<DataPoint>()
        amdSym1.title = "f=.5"
        val amdSym2 = LineGraphSeries<DataPoint>()
        amdSym2.title = "f=.9"
        val amdSym3 = LineGraphSeries<DataPoint>()
        amdSym3.title = "f=.975"
        try {
            for (x in 1..300 step 30) {
                // at r = 256
                var f = 0.5
                amdSym1.appendData(
                    DataPoint(x.toDouble(), asymmetricY(x.toDouble(), f)),
                    true,
                    100
                )
                //at r = 32
                f = .9
                amdSym2.appendData(
                    DataPoint(x.toDouble(), asymmetricY(x.toDouble(), f)),
                    true,
                    100
                )
                //at r = 16
                f = .975
                amdSym3.appendData(
                    DataPoint(x.toDouble(), asymmetricY(x.toDouble(), f)),
                    true,
                    100
                )
            }
            graph.addSeries(amdSym1)
            amdSym2.color = Color.GREEN
            graph.addSeries(amdSym2)
            amdSym3.color = Color.RED
            graph.addSeries(amdSym3)
            initialGraph()
            Toast.makeText(this, "Asymmetric Graph Done", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
        }

    }

    private fun dynamicGraphh() {
        val amdSym1 = LineGraphSeries<DataPoint>()
        amdSym1.title = "f=.5"
        val amdSym2 = LineGraphSeries<DataPoint>()
        amdSym2.title = "f=.9"
        val amdSym3 = LineGraphSeries<DataPoint>()
        amdSym3.title = "f=.975"
        try {
            for (x in 1..300 step 30) {
                // at r = 256
                var f = 0.5
                amdSym1.appendData(DataPoint(x.toDouble(), dynamicY(x.toDouble(), f)), true, 100)
                //at r = 32
                f = .9
                amdSym2.appendData(DataPoint(x.toDouble(), dynamicY(x.toDouble(), f)), true, 100)
                //at r = 16
                f = .975
                amdSym3.appendData(DataPoint(x.toDouble(), dynamicY(x.toDouble(), f)), true, 100)
            }
            graph.addSeries(amdSym1)
            amdSym2.color = Color.GREEN
            graph.addSeries(amdSym2)
            amdSym3.color = Color.RED
            graph.addSeries(amdSym3)
            initialGraph()
            Toast.makeText(this, "Dynamic Graph Done", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
        }
    }


    private fun initialGraph() {
        graph.title = "SpeedUp in Amdahl's Law"
        graph.titleTextSize = 50f
        graph.viewport.isScalable = true
        graph.viewport.isScrollable = true
        graph.legendRenderer.isVisible = true
        graph.animation
    }

    private fun symmetricY(x: Double, f: Double): Double {
        return 1 / ((1 - f) / (kotlin.math.sqrt(x)) + (f * x) / (kotlin.math.sqrt(x) * n))
    }

    private fun asymmetricY(x: Double, f: Double): Double {
        return 1 / (((1 - f) / (kotlin.math.sqrt(x))) + (f / (kotlin.math.sqrt(x) + n - x)))
    }

    private fun dynamicY(x: Double, f: Double): Double {
        return 1 / (((1 - f) / kotlin.math.sqrt(x)) + (f / n))
    }
}

