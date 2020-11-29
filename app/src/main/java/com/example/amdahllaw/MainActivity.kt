package com.example.amdahllaw

import android.R.attr.x
import android.R.attr.y
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.sin
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var amdSym1 = LineGraphSeries<DataPoint>()
        var amdSym2 = LineGraphSeries<DataPoint>()
        var amdSym3 = LineGraphSeries<DataPoint>()

        graphbtn.setOnClickListener {
            try {
                var f = 0.5
                var n = 256.0
                // at r = 256
                for (x in 1..300 step 30) {
                    var y = 1 / ((1 - f) / (kotlin.math.sqrt(x.toDouble())) + (f * x) / (kotlin.math.sqrt(x.toDouble()) * n))
                    amdSym1.appendData(DataPoint(x.toDouble(), y), true, 100)
                }
                // at r = 32
                f=.9
                for (x in 1..300 step 30) {
                    var y = 1 / ((1 - f) / (kotlin.math.sqrt(x.toDouble())) + (f * x) / (kotlin.math.sqrt(x.toDouble()) * n))
                    amdSym2.appendData(DataPoint(x.toDouble(), y), true, 100)
                }

                // at r = 16
                f=.975
                for (x in 1..300 step 30) {
                    var y = 1 / ((1 - f) / (kotlin.math.sqrt(x.toDouble())) + (f * x) / (kotlin.math.sqrt(x.toDouble()) * n))
                    amdSym3.appendData(DataPoint(x.toDouble(), y), true, 100)
                }

                graph.addSeries(amdSym1)
                amdSym2.color = Color.GREEN
                graph.addSeries(amdSym2)
                amdSym3.color = Color.RED
                graph.addSeries(amdSym3)

                intinalizing_graph()
                Toast.makeText(this, "Graph Done", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
            }
        }
    }
    private fun intinalizing_graph() {
        graph.title = "SpeedUp in Amdahl's Law"
        graph.titleTextSize = 50f
        graph.viewport.isScalable = true
        graph.viewport.isScrollable = true
    }
}
