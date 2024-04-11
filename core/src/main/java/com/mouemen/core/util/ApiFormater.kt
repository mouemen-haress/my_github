package com.moemen.core.util

import org.json.JSONException
import org.json.JSONObject

class ApiFormater {
    companion object {

        fun formatApi(response: Any?): String {
            if (response != null) {
                if (response is String) {
                    try {
                        val jb: JSONObject? = parse(response as String?)
                        return if (jb != null) {
                            jb.toString(3)
                        } else {
                            "Parsing Not Completed"
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        return "Parsing Not Completed"
                    }
                } else if (response is JSONObject) {
                    try {
                        return response.toString(3)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        return "Parsing Not Completed"
                    }
                }
            }
            return "Parsing Not Completed"
        }

        fun parse(data: String?): JSONObject? {
            try {
                return if (data == null) null else JSONObject(data)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return null
        }
    }

}