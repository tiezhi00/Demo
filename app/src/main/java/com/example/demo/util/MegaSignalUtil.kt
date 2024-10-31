package com.example.demo.util

import com.example.demo.MyApplication
import mega.car.CarPropertyManager
import mega.car.MegaCarPropHelper
import mega.car.hardware.CarPropertyValue

object MegaSignalUtil {

    private val propHelper =
        MegaCarPropHelper.getInstance(MyApplication.getApplication().applicationContext, null)

    fun setIntProp(propId: Int, value: Int) {
        infoLog("setIntProp: int propId = $propId --- value = $value")
        ThreadUtils.execute {
            propHelper.setIntProp(propId, value)
        }
    }

    fun setIntProp(propId: Int, area: Int, value: Int) {
        infoLog("setIntProp: int propId = $propId --- area = $area --- value = $value")
        ThreadUtils.execute {
            propHelper.setIntProp(propId, area, value)
        }
    }

    fun setFloatProp(propId: Int, area: Int, value: Float) {
        infoLog("setFloatProp: int propId = $propId --- area = $area --- value = $value")
        ThreadUtils.execute {
            propHelper.setFloatProp(propId, area, value)
        }
    }

    fun setFloatProp(propId: Int, value: Float) {
        infoLog("setFloatProp: int propId = $propId --- value = $value")
        ThreadUtils.execute {
            propHelper.setFloatProp(propId, value)
        }
    }

    fun setRawProp(value: CarPropertyValue<*>) {
        infoLog("setRawProp: propId = ${value.propertyId}, value = ${value.value}")
        ThreadUtils.execute {
            propHelper.setRawProp(value)
        }
    }

    fun getIntProp(propId: Int): Int {
        val value = propHelper.getIntProp(propId)
        infoLog("getIntProperty : ------ propId : $propId --- value : $value")
        return value
    }

    fun getIntProp(propId: Int, area: Int): Int {
        val value = propHelper.getIntProp(propId, area)
        infoLog("getIntAreaProperty : ------ propId : $propId --- area = $area --- value : $value")
        return value
    }

    fun getFloatProp(propId: Int, area: Int): Float {
        val value = propHelper.getFloatProp(propId, area)
        infoLog("getFloatAreaProperty : ------ propId : $propId --- value : $value")
        return value
    }

    fun getFloatProp(propId: Int): Float {
        val value = propHelper.getFloatProp(propId)
        infoLog("getFloatProperty : ------ propId : $propId --- value : $value")
        return value
    }

    fun getPropertyRaw(propId: Int): CarPropertyValue<*>? {
        infoLog("getPropertyRaw : ------ propId : $propId")
        return propHelper.getPropertyRaw(propId)
    }

    fun getPropertyRaw(propId: Int, area: Int): CarPropertyValue<*>? {
        infoLog("getPropertyRaw : ------ propId : $propId")
        return propHelper.getPropertyRaw(propId, area)
    }

    /**********************注册与解除回调**********************/

    fun registerCallback(
        callback: CarPropertyManager.CarPropertyEventCallback,
        propertyIds: Set<Int?>
    ) {
        infoLog("addCallback: int propertyIds.size() = ${propertyIds.size}")
        propHelper.registerCallback(callback, propertyIds)
    }

    fun <T> unregisterCallback(callback: T, propertyIds: Set<Int?>) {
        infoLog("removeCallback: int propertyIds.size() = ${propertyIds.size}")
        propHelper.unregisterCallback(callback, propertyIds)
    }
}