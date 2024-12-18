package com.example.testexam.domain

import com.example.testexam.models.database.Memes
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object Constans {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://pxgveulqfwdwmzokgwzo.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InB4Z3ZldWxxZndkd216b2tnd3pvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzQyOTk1MTgsImV4cCI6MjA0OTg3NTUxOH0.-BSBH_LprKEvytU-7wG5tHUDGqVL9XAkkfhWtIZN1Yc"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
    var currentUser: String = ""
    var currentMeme: Memes = Memes()
}