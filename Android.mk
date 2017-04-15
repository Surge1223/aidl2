LOCAL_PATH:= $(call my-dir)
common_cppflags := \
	-std=c++11 -Wall \
	-Werror -Wno-unused-variable \
	-Wno-unused-parameter \
	-Wno-unused-private-field


common_includes := \
	$(LOCAL_PATH) \
	$(LOCAL_PATH)/include \
	system/core/libutils/include \
	system/core/include \
	frameworks/native/include

include $(CLEAR_VARS)
TARGET_2ND_ARCH := arm64
LOCAL_SRC_FILES:= \
	IInterfacerInterface.cpp

LOCAL_MODULE:= interfacer

LOCAL_AIDL_INCLUDES := \
    frameworks/native/aidl/binder \
    frameworks/base/core/java \
    $(LOCAL_PATH)/include/projekt/substratum \

LOCAL_EXPORT_C_INCLUDE_DIRS := \
    $(LOCAL_PATH)/binder
LOCAL_CPPFLAGS := $(common_cppflags)

LOCAL_CERTIFICATE := platform
LOCAL_C_INCLUDES := \
	$(common_includes) \
	$(LOCAL_PATH)/include \
	$(JNI_H_INCLUDE)
LOCAL_C_INCLUDES += $(JNI_H_INCLUDE)
LOCAL_PRELINK_MODULE := false
LOCAL_SHARED_LIBRARIES := libbinder libutils libcutils liblog
LOCAL_CFLAGS := -DPLATFORM
LOCAL_CFLAGS += -Wall -Werror -Iinclude
include $(BUILD_SHARED_LIBRARY)


