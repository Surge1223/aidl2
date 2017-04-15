#ifndef AIDL_GENERATED_PROJEKT_SUBSTRATUM_BP_INTERFACER_INTERFACE_H_
#define AIDL_GENERATED_PROJEKT_SUBSTRATUM_BP_INTERFACER_INTERFACE_H_

#include <binder/IBinder.h>
#include <binder/IInterface.h>
#include <utils/Errors.h>
#include <projekt/substratum/IInterfacerInterface.h>

namespace projekt {

namespace substratum {

class BpInterfacerInterface : public ::android::BpInterface<IInterfacerInterface> {
public:
explicit BpInterfacerInterface(const ::android::sp<::android::IBinder>& _aidl_impl);
virtual ~BpInterfacerInterface() = default;
::android::binder::Status installPackage(const ::std::vector<::android::String16>& paths) override;
::android::binder::Status uninstallPackage(const ::std::vector<::android::String16>& packages, bool restartUi) override;
::android::binder::Status restartSystemUI() override;
::android::binder::Status configurationShim() override;
::android::binder::Status applyBootanimation(const ::android::String16& name) override;
::android::binder::Status applyFonts(const ::android::String16& pid, const ::android::String16& fileName) override;
::android::binder::Status applyAudio(const ::android::String16& pid, const ::android::String16& fileName) override;
::android::binder::Status enableOverlay(const ::std::vector<::android::String16>& packages, bool restartUi) override;
::android::binder::Status disableOverlay(const ::std::vector<::android::String16>& packages, bool restartUi) override;
::android::binder::Status changePriority(const ::std::vector<::android::String16>& packages, bool restartUi) override;
::android::binder::Status copy(const ::android::String16& source, const ::android::String16& destination) override;
::android::binder::Status move(const ::android::String16& source, const ::android::String16& destination) override;
::android::binder::Status mkdir(const ::android::String16& destination) override;
::android::binder::Status deleteDirectory(const ::android::String16& directory, bool withParent) override;
::android::binder::Status applyProfile(const ::std::vector<::android::String16>& enable, const ::std::vector<::android::String16>& disable, const ::android::String16& name, bool restartUi) override;
};  // class BpInterfacerInterface

}  // namespace substratum

}  // namespace projekt

#endif  // AIDL_GENERATED_PROJEKT_SUBSTRATUM_BP_INTERFACER_INTERFACE_H_