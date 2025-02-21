# EXTRA: Inconsistency of set-null Behavior across Each Type of Association

## Nullifying FK

### 1-1
* parent.setParent(null)
### 1-M
* child.setParent(null)
* Useless: 
  * parent.setChild(null)
  * parent.getChildren.remove(child)


## Verdict: 

* JPA is too creative.
* No need to memorize patterns. Feel free to forget.
* Best way? Just test it before implementation.