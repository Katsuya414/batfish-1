package org.batfish.representation.frr;

import static com.google.common.base.MoreObjects.firstNonNull;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nonnull;
import org.batfish.datamodel.ConcreteInterfaceAddress;

public class MockOutOfBandConfiguration implements OutOfBandConfiguration {

  private Set<String> _interfaces;
  private Map<String, String> _interfaceVrf;
  private Map<String, List<ConcreteInterfaceAddress>> _interfaceAddresses;
  private Set<String> _vrfs;
  private Map<String, Vxlan> _vxlans;
  private Map<Integer, String> _vlanVrfs;

  @Override
  public boolean hasInterface(String ifaceName) {
    return _interfaces.contains(ifaceName);
  }

  @Override
  public String getInterfaceVrf(String ifaceName) {
    return _interfaceVrf.get(ifaceName);
  }

  @Override
  public @Nonnull List<ConcreteInterfaceAddress> getInterfaceAddresses(String ifaceName) {
    return _interfaceAddresses.get(ifaceName);
  }

  @Override
  public boolean hasVrf(String vrfName) {
    return _vrfs.contains(vrfName);
  }

  @Override
  public Map<String, Vxlan> getVxlans() {
    return _vxlans;
  }

  @Override
  public Optional<String> getVrfForVlan(Integer bridgeAccessVlan) {
    return Optional.of(_vlanVrfs.get(bridgeAccessVlan));
  }

  public static Builder builder() {
    return new Builder();
  }

  // always build via the builder
  private MockOutOfBandConfiguration() {}

  public static final class Builder {
    private Map<String, String> _superInterfaceNames;
    private Set<String> _interfaces;
    private Map<String, String> _interfaceVrf;
    private Map<String, List<ConcreteInterfaceAddress>> _interfaceAddresses;
    private Set<String> _vrfs;
    private Map<String, Vxlan> _vxlans;
    private Map<Integer, String> _vlanVrfs;

    private Builder() {}

    public Builder setSuperInterfaceNames(Map<String, String> superInterfaceNames) {
      this._superInterfaceNames = superInterfaceNames;
      return this;
    }

    public Builder setInterfaces(Set<String> interfaces) {
      this._interfaces = interfaces;
      return this;
    }

    public Builder setInterfaceVrf(Map<String, String> interfaceVrf) {
      this._interfaceVrf = interfaceVrf;
      return this;
    }

    public Builder setInterfaceAddresses(
        Map<String, List<ConcreteInterfaceAddress>> interfaceAddresses) {
      this._interfaceAddresses = interfaceAddresses;
      return this;
    }

    public Builder setHasVrf(Set<String> vrfs) {
      this._vrfs = vrfs;
      return this;
    }

    public Builder setVxlans(Map<String, Vxlan> vxlans) {
      this._vxlans = vxlans;
      return this;
    }

    public Builder setVlanVrfs(Map<Integer, String> vlanVrfs) {
      this._vlanVrfs = vlanVrfs;
      return this;
    }

    public MockOutOfBandConfiguration build() {
      MockOutOfBandConfiguration mockOutOfBandConfiguration = new MockOutOfBandConfiguration();
      mockOutOfBandConfiguration._vxlans = firstNonNull(this._vxlans, ImmutableMap.of());
      mockOutOfBandConfiguration._vlanVrfs = firstNonNull(this._vlanVrfs, ImmutableMap.of());
      mockOutOfBandConfiguration._interfaceVrf =
          firstNonNull(this._interfaceVrf, ImmutableMap.of());
      mockOutOfBandConfiguration._interfaceAddresses =
          firstNonNull(this._interfaceAddresses, ImmutableMap.of());
      mockOutOfBandConfiguration._vrfs = firstNonNull(this._vrfs, ImmutableSet.of());
      mockOutOfBandConfiguration._interfaces = firstNonNull(this._interfaces, ImmutableSet.of());
      return mockOutOfBandConfiguration;
    }
  }
}