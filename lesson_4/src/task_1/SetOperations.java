package task_1;

import java.util.Set;

/**
 * Interface: SetOperations
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 02.10.13
 * Time: 11:40
 * Mail: ysb.kanivtsi@gmail.com
 */
public interface SetOperations {

    public boolean equals(Set a, Set b);  //Два множества А и В равны (А=В), если они состоят из одних и тех же элементов

    public Set union(Set a, Set b); //Объединением (суммой) множеств А и В называется множество А ∪В,  элементы которого принадлежат хотя бы одному из этих множеств

    public Set subtract(Set a, Set b); //Разностью множеств А и В называется множество АВ, элементы которого принадлежат множеству А, но не принадлежат множеству В

    public Set intersect(Set a, Set b); //Пересечением (произведением) множеств А и В называется множество А ∩ В, элементы которого принадлежат как множеству А, так и множеству В

    public Set symmetricSubtract(Set a, Set b); //Симметричной разностью множеств А и В называется множество А Δ В,  являющееся объединением разностей множеств АВ и ВА, то есть А Δ В = (АВ) ∪(ВА)
}