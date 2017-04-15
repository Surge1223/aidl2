#ifndef AIDL_GENERATED_PROJEKT_SUBSTRATUM_I_INTERFACER_INTERFACE_H_
#define AIDL_GENERATED_PROJEKT_SUBSTRATUM_I_INTERFACER_INTERFACE_H_

#include <binder/IBinder.h>
#include <binder/IInterface.h>
#include <binder/Status.h>
#include <utils/String16.h>
#include <utils/StrongPointer.h>
#include <vector>
#include <cstdint>

namespace projekt {

namespace substratum {

class IInterfacerInterface : public ::android::IInterface {
public:
DECLARE_META_INTERFACE(InterfacerInterface);
virtual ::android::binder::Status installPackage(const ::std::vector<::android::String16>& paths) = 0;
virtual ::android::binder::Status uninstallPackage(const ::std::vector<::android::String16>& packages, bool restartUi) = 0;
virtual ::android::binder::Status restartSystemUI() = 0;
virtual ::android::binder::Status configurationShim() = 0;
virtual ::android::binder::Status applyBootanimation(const ::android::String16& name) = 0;
virtual ::android::binder::Status applyFonts(const ::android::String16& pid, const ::android::String16& fileName) = 0;
virtual ::android::binder::Status applyAudio(const ::android::String16& pid, const ::android::String16& fileName) = 0;
virtual ::android::binder::Status enableOverlay(const ::std::vector<::android::String16>& packages, bool restartUi) = 0;
virtual ::android::binder::Status disableOverlay(const ::std::vector<::android::String16>& packages, bool restartUi) = 0;
virtual ::android::binder::Status changePriority(const ::std::vector<::android::String16>& packages, bool restartUi) = 0;
virtual ::android::binder::Status copy(const ::android::String16& source, const ::android::String16& destination) = 0;
virtual ::android::binder::Status move(const ::android::String16& source, const ::android::String16& destination) = 0;
virtual ::android::binder::Status mkdir(const ::android::String16& destination) = 0;
virtual ::android::binder::Status deleteDirectory(const ::android::String16& directory, bool withParent) = 0;
virtual ::android::binder::Status applyProfile(const ::std::vector<::android::String16>& enable, const ::std::vector<::android::String16>& disable, const ::android::String16& name, bool restartUi) = 0;
enum Call {
  INSTALLPACKAGE = ::android::IBinder::FIRST_CALL_TRANSACTION + 0,
  UNINSTALLPACKAGE = ::android::IBinder::FIRST_CALL_TRANSACTION + 1,
  RESTARTSYSTEMUI = ::android::IBinder::FIRST_CALL_TRANSACTION + 2,
  CONFIGURATIONSHIM = ::android::IBinder::FIRST_CALL_TRANSACTION + 3,
  APPLYBOOTANIMATION = ::android::IBinder::FIRST_CALL_TRANSACTION + 4,
  APPLYFONTS = ::android::IBinder::FIRST_CALL_TRANSACTION + 5,
  APPLYAUDIO = ::android::IBinder::FIRST_CALL_TRANSACTION + 6,
  ENABLEOVERLAY = ::android::IBinder::FIRST_CALL_TRANSACTION + 7,
  DISABLEOVERLAY = ::android::IBinder::FIRST_CALL_TRANSACTION + 8,
  CHANGEPRIORITY = ::android::IBinder::FIRST_CALL_TRANSACTION + 9,
  COPY = ::android::IBinder::FIRST_CALL_TRANSACTION + 10,
  MOVE = ::android::IBinder::FIRST_CALL_TRANSACTION + 11,
  MKDIR = ::android::IBinder::FIRST_CALL_TRANSACTION + 12,
  DELETEDIRECTORY = ::android::IBinder::FIRST_CALL_TRANSACTION + 13,
  APPLYPROFILE = ::android::IBinder::FIRST_CALL_TRANSACTION + 14,
};
};  // class IInterfacerInterface

}  // namespace substratum

}  // namespace projekt

#endif  // AIDL_GENERATED_PROJEKT_SUBSTRATUM_I_INTERFACER_INTERFACE_H_
