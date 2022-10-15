package com.example.composetutorial

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
//import android.graphics.Color
//import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ComposeTutorialTheme{

                Conversation(MessageData.MessageList)

            }
        }
    }
}

data class Message(val sender: String, val body: String)

@Composable
fun Conversation(messageList: List<Message>){
    LazyColumn{
        items(messageList){
                message -> MessageBox(message, "Jetpack Compose ")
        }
    }
}

@Composable
fun MessageBox(msg: Message, name: String){
    Row(modifier = Modifier.padding(all=8.dp)){
        Image(painter = painterResource(id = R.drawable.blankprofile),
            contentDescription= "profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

    Spacer(modifier = Modifier.width(8.dp))

    var msgExpanded by remember { mutableStateOf(false)}
    //val surfaceColorChange : Color by animateColorAsState(
        //if(msgExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    //)

    Column(modifier = Modifier.clickable { msgExpanded = !msgExpanded}) {
        Text(
            text = msg.sender,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.subtitle2
        )

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 1.dp,
            //color = surfaceColorChange,
            modifier = Modifier.animateContentSize().padding(1.dp)
        ) {
            Text(
                text = msg.body,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(all = 4.dp),
                maxLines = if(msgExpanded) Int.MAX_VALUE else 1
            )
           }
    }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
showBackground = true,
name = "Dark Mode")
@Composable
fun PreviewMessageBox(){
    ComposeTutorialTheme{
        Conversation(messageList = MessageData.MessageList)
    }
}

@Preview(name = "Light Mode")
@Composable
fun PreviewLightMessageBox(){
    ComposeTutorialTheme{
        Conversation(messageList = MessageData.MessageList)

    }
}