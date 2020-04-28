package com.meng.datastrcture;

/**
 * 平衡树在插入和删除的时候，会通过旋转操作将高度保持在logN。
 * 其中两款具有代表性的平衡树分别为AVL树和红黑树。
 * AVL树由于实现比较复杂，而且插入和删除性能差，在实际环境下的应用不如红黑树。
 *
 * 红黑树（Red-Black Tree，以下简称RBTree）的实际应用非常广泛，
 * 比如Linux内核中的完全公平调度器、高精度计时器、ext3文件系统等等，
 * 各种语言的函数库如Java的TreeMap和TreeSet，C++ STL的map、multimap、multiset等。
 *
 * 《算法导论》中对于红黑树的定义如下：
 * 1. 每个结点或是红的，或是黑的
 * 2. 根节点是黑的
 * 3. 每个叶结点是黑的
 * 4. 如果一个结点是红的，则它的两个儿子都是黑的
 * 5. 对每个结点，从该结点到其子孙节点的所有路径上包含相同数目的黑结点
 * 对与第4点，网上有些定义是：父子节点之间不能出现两个连续的红节点，这种定义和《算法导论》中定义的效果是一样的
 *
 * RBTree在理论上还是一棵BST树，但是它在对BST的插入和删除操作时会维持树的平衡，
 * 即保证树的高度在[logN,logN+1]（理论上，极端的情况下可以出现RBTree的高度达到2*logN，但实际上很难遇到）。
 * 这样RBTree的查找时间复杂度始终保持在O(logN)从而接近于理想的BST。
 * RBTree的删除和插入操作的时间复杂度也是O(logN)。
 * RBTree的查找操作就是BST的查找操作。
 */
public class RedBlackTree {

}
