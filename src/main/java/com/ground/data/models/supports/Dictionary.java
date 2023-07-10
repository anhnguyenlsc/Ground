package com.ground.data.models.supports;

public class Dictionary {


  private final String key;
  private final String label;
  private String description;

  {
     Dictionary.of("key", "label");
  }

  private Dictionary(String key, String label) {
    this.key = key;
    this.label = label;
  }

  public static  Dictionary of(String key, String label) {
    return new  Dictionary(key, label);
  }

  public String getKey() {
    return this.key;
  }

  public String getLabel() {
    return this.label;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof  Dictionary)) return false;
    final  Dictionary other = ( Dictionary) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$key = this.getKey();
    final Object other$key = other.getKey();
    if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
    final Object this$label = this.getLabel();
    final Object other$label = other.getLabel();
    if (this$label == null ? other$label != null : !this$label.equals(other$label)) return false;
    final Object this$description = this.getDescription();
    final Object other$description = other.getDescription();
    if (this$description == null ? other$description != null : !this$description.equals(other$description)) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof  Dictionary;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $key = this.getKey();
    result = result * PRIME + ($key == null ? 43 : $key.hashCode());
    final Object $label = this.getLabel();
    result = result * PRIME + ($label == null ? 43 : $label.hashCode());
    final Object $description = this.getDescription();
    result = result * PRIME + ($description == null ? 43 : $description.hashCode());
    return result;
  }

  public String toString() {
    return "Dictionary(key=" + this.getKey() + ", label=" + this.getLabel() + ", description=" + this.getDescription() + ")";
  }


}
