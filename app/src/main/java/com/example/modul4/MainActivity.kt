package com.example.modul4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.modul4.ui.theme.Modul4Theme
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContent {
//            Modul4Theme {
//                val snackbarHostState = remember { SnackbarHostState() }
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
//                ) { innerPadding ->
//                    var number by rememberSaveable { mutableStateOf(1) }
//                    CounterScreen(Modifier.padding(32.dp),
//                        number = number,
//                        label = "Double",
//                    onButtonClick = { number *= 2 }
//                    )
//                    LaunchedEffect(number) {
//                        snackbarHostState.showSnackbar("Number $number is shown!")
//                    }
//                    LaunchedEffect(Unit) {
//                        number = Repo.getData()
//                    }
//                }
//            }
//        }
        setContent {
            var hargaTiket by remember { mutableStateOf(50000) }
            var jumlahTiket by remember { mutableStateOf(1) }
            var namaPembeli by remember { mutableStateOf("") }
            var status by remember { mutableStateOf("Silakan pesan tiket") }
            var sedangMemesan by remember { mutableStateOf(false) }
            LaunchedEffect(sedangMemesan) {
                if (sedangMemesan) {
                    delay(5000)
                    status = "Tiket telah dipesan"
                    sedangMemesan = false
                }
            }
            TicketScreen(
                hargaTiket = hargaTiket,
                jumlahTiket = jumlahTiket,
                namaPembeli = namaPembeli,
                status = status,
                sedangMemesan = sedangMemesan,
                onNamaChange = {
                    namaPembeli = it },
                onTambah = {
                    jumlahTiket++ },
                onKurang = {
                    if (jumlahTiket > 1) {
                        jumlahTiket-- } },
                onPesan = {
                    if (namaPembeli.isBlank()) {
                        status = "Nama Masih Kosong"
                    } else {
                        status = "Memproses pesanan........."
                        sedangMemesan = true
                    }
                }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Modul4Theme {
        Greeting("Android")
    }
}

@Composable
fun CounterScreen(modifier: Modifier,
                  number: Int, label: String, onButtonClick: () -> Unit){
//    var number by remember { mutableStateOf(0) }
//    val label by remember { mutableStateOf("Increment")}
    Column(modifier) {
        Text(text = "$number", fontSize = 72.sp)
        Button(onClick = onButtonClick) {
            Text(text = "$label")
        }
    }
}
@Composable
fun TicketScreen(
    hargaTiket: Int,
    jumlahTiket: Int,
    namaPembeli: String,
    status: String,
    sedangMemesan: Boolean,
    onNamaChange: (String) -> Unit,
    onTambah: () -> Unit,
    onKurang: () -> Unit,
    onPesan: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pemesanan Tiket",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Nama Pembeli",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = namaPembeli,
            onValueChange = onNamaChange,
            placeholder = {
                Text(
                    text = "Masukkan nama Anda",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Harga Tiket: Rp$hargaTiket",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Jumlah Tiket",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onKurang
            ) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(35.dp))
            Text(
                text = "$jumlahTiket",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(35.dp))
            Button(
                onClick = onTambah
            ) {
                Text("+")
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Total: Rp${hargaTiket * jumlahTiket}",
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = onPesan,
            modifier = Modifier.fillMaxWidth(),
            enabled = !sedangMemesan
        ) {
            Text(
                text = if (sedangMemesan) {
                    "Memproses..."
                } else {
                    "Pesan Tiket"
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Status: $status",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp
        )
    }
}