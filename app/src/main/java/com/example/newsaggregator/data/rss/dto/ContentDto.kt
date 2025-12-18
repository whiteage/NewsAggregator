package com.example.newsaggregator.data.rss.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlElement



@Serializable
@XmlSerialName("content", "http://search.yahoo.com/mrss/", "media")
data class ContentDto (
    val type: String?,
    val width: String?,
    val url: String,
    val credit: CreditDto?,
    @XmlElement(true)
    @XmlSerialName("description", "http://search.yahoo.com/mrss/", "media")
    val description: String? = null,
)