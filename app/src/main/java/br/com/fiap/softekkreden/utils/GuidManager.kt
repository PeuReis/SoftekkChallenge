package br.com.fiap.softekkreden.utils

import android.content.Context
import java.util.UUID

object GuidManager {
    private const val PREFS_NAME = "app_prefs"
    private const val KEY_GUID = "device_guid"

    fun getOrCreateGuid(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Se já existir um GUID salvo, retorna
        prefs.getString(KEY_GUID, null)?.let { return it }

        // Se não existir, gera um novo e salva
        val newGuid = UUID.randomUUID().toString()
        prefs.edit().putString(KEY_GUID, newGuid).apply()
        return newGuid
    }
}
