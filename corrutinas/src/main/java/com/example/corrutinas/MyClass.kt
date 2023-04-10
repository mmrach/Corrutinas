package com.example.corrutinas

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.system.measureTimeMillis

data class Persona(val nombre: String, val edad: Int)

fun retornarPersona(): Flow<Persona> = flow {
    val lista = listOf(
        Persona("diego", 53),
        Persona("juan", 33),
        Persona("ana", 33)
    )
    for (elemento in lista) {
        delay(1000)
        emit(elemento)
    }
}

fun main(args: Array<String>) {
// //EJERCICIO 1
//    GlobalScope.launch {
//        for(x in 1..10) {
//            print("$x -")
//            delay(1000)
//        }
//    }
//    println("Se bloquea el hilo principal del programa al llamar a readLine")
//    readLine()

//EJERCICIO 2
//    GlobalScope.launch {
//        for(x in 1..10) {
//            print("$x ")
//            delay(1000)
//        }
//    }
//    GlobalScope.launch {
//        for(x in 11..20) {
//            print("$x ")
//            delay(1000)
//        }
//    }
//    readLine()
    
////EJERCICIO 3
//    val numero = Random.nextInt(1, 100)
//    var inicio = 1
//    var fin = 100
//    GlobalScope.launch {
//        var valor:Int
//        do {
//            valor = Random.nextInt(inicio, fin)
//            println(valor)
//            if (valor == numero)
//                println("En número es el $valor")
//            else
//                if (valor < numero) {
//                    println("El numero es mayor")
//                    inicio = valor
//                } else {
//                    println("El numero es menor")
//                    fin = valor
//                }
//            delay(500)
//        } while (valor != numero)
//    }
//    readLine() //detenemos el hilo principal del programa

////EJERCICIO 4
//    runBlocking {
//        launch {
//            delay(1000)
//            println("Paso un segundo")
//        }
//    }
//    println("Iniciando")

////Refactorización de funciones.
//    runBlocking {
//        launch {
//            espera()
//        }
//    }
//    println("Iniciando")

////La corrutinas son livianas.
//    runBlocking {
//        for (x in 1..100000)
//            launch {
//                delay(1000)
//                print(".")
//            }
//    }

//////Manejador que retorna launch
//    runBlocking {
//        val corrutina1=launch {
//            delay(1000)
//            println("Pasó un segundo")
//        }
//        corrutina1.join()
//        val corrutina2=launch {
//            delay(1000)
//            println("Pasó otro segundo")
//        }
//        corrutina2.join()
//        println("Finalizado")
//    }

////runBlocking y coroutineScope
//    runBlocking {
//        Tareas(1)
//        Tareas(2)
//        println("Fin de todas las tareas")
//    }

//// Funciones de suspensión (suspend fun)
//    runBlocking {
//        val d1=dato1()
//        println("Fin de la primera función de suspensión")
//        val d2=dato2()
//        println("Fin de la segundo función de suspensión")
//        print(d1+d2)
//    }

//// LLamadas concurrentes
//    runBlocking {
//        val tiempo1 = System.currentTimeMillis()
//        val corrutina1=async { dato1() }
//        val corrutina2=async { dato2() }
//        println(corrutina1.await()+corrutina2.await())
//        val tiempo2 = System.currentTimeMillis()
//        println("Tiempo total ${tiempo2-tiempo1} ms")
//    }

//// Lazily started async
//   runBlocking {
//       val time = measureTimeMillis {
//           val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
//           val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
//           // some computation
//           one.start() // start the first one
//           two.start() // start the second one
//           println("The answer is ${one.await() + two.await()}")
//       }
//       println("Completed in $time ms")
//    }



} //fin del Main


suspend fun espera() {
    delay(1000)
    println("Pasó un segundo")
}

suspend fun Tareas(nro:Int) {
    coroutineScope {
        launch {
            delay(1000)
            println("Tarea $nro parte A")
        }
        launch {
            delay(2000)
            println("Tarea $nro parte B")
        }
        println("Esperando finalizar las dos tareas $nro")
    }
}

suspend fun dato1(): Int {
    delay(3000)
    return 3
}

suspend fun dato2(): Int {
    delay(3000)
    return 3
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

