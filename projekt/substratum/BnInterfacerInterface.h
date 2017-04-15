#ifndef AIDL_GENERATED_PROJEKT_SUBSTRATUM_BN_INTERFACER_INTERFACE_H_
#define AIDL_GENERATED_PROJEKT_SUBSTRATUM_BN_INTERFACER_INTERFACE_H_

#include <binder/IInterface.h>
#include <projekt/substratum/IInterfacerInterface.h>

namespace projekt {

namespace substratum {

class BnInterfacerInterface : public ::android::BnInterface<IInterfacerInterface> {
public:
::android::status_t onTransact(uint32_t _aidl_code, const ::android::Parcel& _aidl_data, ::android::Parcel* _aidl_reply, uint32_t _aidl_flags = 0) override;
};  // class BnInterfacerInterface

}  // namespace substratum

}  // namespace projekt

#endif  // AIDL_GENERATED_PROJEKT_SUBSTRATUM_BN_INTERFACER_INTERFACE_H_